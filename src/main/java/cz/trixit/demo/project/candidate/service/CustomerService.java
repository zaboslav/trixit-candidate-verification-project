package cz.trixit.demo.project.candidate.service;

import java.math.BigInteger;
import java.util.List;

import cz.trixit.demo.project.candidate.dto.CustomerDetailResponse;
import cz.trixit.demo.project.candidate.dto.CustomerRequest;
import cz.trixit.demo.project.candidate.dto.CustomerResponse;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;

public interface CustomerService {

	List<CustomerResponse> getCustomers();

	CustomerResponse createCustomer(CustomerRequest request);

	CustomerDetailResponse getCustomer(BigInteger customerId);

	void setCurrency(BigInteger customerId, SupportedCurrencyType currency);

	void removeCurrency(BigInteger customerId, SupportedCurrencyType currency);

	void updateCustomer(BigInteger customerId, CustomerRequest request);

	void deteleCustomer(BigInteger customerId);

}
