package cz.trixit.demo.project.candidate.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cz.trixit.demo.project.candidate.dao.CustomerRepository;
import cz.trixit.demo.project.candidate.dao.WatchedCurrencyRepository;
import cz.trixit.demo.project.candidate.dto.CustomerDetailResponse;
import cz.trixit.demo.project.candidate.dto.CustomerRequest;
import cz.trixit.demo.project.candidate.dto.CustomerResponse;
import cz.trixit.demo.project.candidate.exception.ConflictException;
import cz.trixit.demo.project.candidate.exception.NotFoundException;
import cz.trixit.demo.project.candidate.mapper.CustomerMapper;
import cz.trixit.demo.project.candidate.model.CustomerEntity;
import cz.trixit.demo.project.candidate.model.WatchedCurrencyEntity;
import cz.trixit.demo.project.candidate.model.enums.AuditLogEventType;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import cz.trixit.demo.project.candidate.service.AuditLogService;
import cz.trixit.demo.project.candidate.service.CustomerService;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;
	private AuditLogService auditLogService;
	private WatchedCurrencyRepository watchedCurrencyRepository;

	@Override
	public List<CustomerResponse> getCustomers() {
		return this.customerRepository.findAll().stream().map(customerMapper::mapCustomer).toList();
	}

	@Override
	public CustomerResponse createCustomer(CustomerRequest request) {
		logger.info("Creating customer {}", request.getCustomerName());
		try {
			CustomerEntity customer = new CustomerEntity();
			customer.setName(request.getCustomerName());
			customer = this.customerRepository.save(customer);
			this.auditLogService.write(AuditLogEventType.CUSTOMER_CREATED, request.getCustomerName());
			return this.customerMapper.mapCustomer(customer);
		} catch (RuntimeException e) {
			this.auditLogService.writeError(AuditLogEventType.CUSTOMER_CREATED, e, request.getCustomerName());
			logger.error("Unable to create customer.", e);
			throw e;
		}
	}
	
	@Override
	public void updateCustomer(BigInteger customerId, CustomerRequest request) {
		logger.info("Updating customer id = {}, name = {}", customerId, request.getCustomerName());
		CustomerEntity entity = this.getCustomerEntity(customerId);
		String previousName = "unknown";
		try {
			previousName = entity.getName();
			entity.setName(request.getCustomerName());
			this.auditLogService.write(AuditLogEventType.CUSTOMER_UPDATED, previousName, request.getCustomerName());
		} catch (RuntimeException e) {
			this.auditLogService.writeError(AuditLogEventType.CUSTOMER_UPDATED, e, previousName, request.getCustomerName());
			logger.error("Unable to update customer.", e);
			throw e;
		}
	}

	@Override
	public void deteleCustomer(BigInteger customerId) {
		logger.info("Deleting customer id = {}", customerId);
		CustomerEntity entity = this.getCustomerEntity(customerId);
		String previousName = null;
		try {
			previousName = entity.getName();
			this.customerRepository.delete(entity);
			this.auditLogService.write(AuditLogEventType.CUSTOMER_DELETED, previousName, previousName);
		} catch (RuntimeException e) {
			this.auditLogService.writeError(AuditLogEventType.CUSTOMER_DELETED, e, previousName, previousName);
			logger.error("Unable to update customer.", e);
			throw e;
		}
	}

	@Override
	public CustomerDetailResponse getCustomer(BigInteger customerId) {
		logger.info("Customer detail request: {}", customerId);
		CustomerEntity entity = getCustomerEntity(customerId);
		return this.customerMapper.mapCustomerDetail(entity);
	}

	@Override
	public void setCurrency(BigInteger customerId, SupportedCurrencyType currency) {
		logger.info("Setting currency {} for customer {}", currency, customerId);
		try {
			CustomerEntity customer = getCustomerEntity(customerId);
			if (customer.getCurrencies().stream().anyMatch(i -> i.getCurrency() == currency)) {
				throw new ConflictException("DUPLICATE_CURRENCY", "Currency already assigned");
			}
			WatchedCurrencyEntity entity = new WatchedCurrencyEntity();
			entity.setCustomer(customer);
			entity.setCurrency(currency);
			customer.getCurrencies().add(entity);
			this.auditLogService.write(AuditLogEventType.CURRENCY_ADDED, currency, customerId);
		} catch (RuntimeException e) {
			this.auditLogService.writeError(AuditLogEventType.CURRENCY_ADDED, e, currency, customerId);
			logger.error("Unable to set currency.", e);
			throw e;
		}
	}

	@Override
	public void removeCurrency(BigInteger customerId, SupportedCurrencyType currency) {
		logger.info("Removing currency {} for customer {}", currency, customerId);
		try {
			int deleted = watchedCurrencyRepository.deleteByCustomerIdAndCurrency(customerId, currency);
			if (deleted == 0) {
				throw new NotFoundException("NO_CURRENCY", "Currency is not watched by customer");
			}
			this.auditLogService.write(AuditLogEventType.CURRENCY_REMOVED, currency, customerId);
		} catch (RuntimeException e) {
			this.auditLogService.writeError(AuditLogEventType.CURRENCY_REMOVED, e, currency, customerId);
			logger.error("Unable to remove currency.", e);
			throw e;
		}
	}

	private CustomerEntity getCustomerEntity(BigInteger customerId) {
		return this.customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("NO_CUSTOMER", "Customer does not exist."));
	}

}
