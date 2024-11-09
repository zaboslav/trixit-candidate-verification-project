package cz.trixit.demo.project.candidate.rest.impl;

import java.math.BigInteger;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import cz.trixit.demo.project.candidate.dto.CustomerDetailResponse;
import cz.trixit.demo.project.candidate.dto.CustomerRequest;
import cz.trixit.demo.project.candidate.dto.CustomerResponse;
import cz.trixit.demo.project.candidate.dto.WatchedCurrencyResponse;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import cz.trixit.demo.project.candidate.rest.CustomerController;
import cz.trixit.demo.project.candidate.service.CustomerService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/customers")
@Validated
@AllArgsConstructor
public class CustomerControllerImpl implements CustomerController {

	private CustomerService customerService;

	@Override
	@PostMapping
	public ResponseEntity<Void> createCustomer(@RequestBody CustomerRequest request) {
		CustomerResponse customer = this.customerService.createCustomer(request);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(customer.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@Override
	@GetMapping
	public ResponseEntity<List<CustomerResponse>> getCustomers() {
		return ResponseEntity.ok(this.customerService.getCustomers());
	}

	@Override
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDetailResponse> getCustomer(@PathVariable BigInteger customerId) {
		return ResponseEntity.ok(this.customerService.getCustomer(customerId));
	}
	
	@Override
	@PostMapping("/{customerId}")
	public ResponseEntity<CustomerDetailResponse> updateCustomer(@PathVariable BigInteger customerId, @RequestBody CustomerRequest request) {
		customerService.updateCustomer(customerId, request);
		return ResponseEntity.ok(this.customerService.getCustomer(customerId));
	}
	
	@Override
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable BigInteger customerId) {
		customerService.deteleCustomer(customerId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@GetMapping("/{customerId}/currencies")
	public ResponseEntity<List<WatchedCurrencyResponse>> getCustomerRates(@PathVariable BigInteger customerId) {
		return ResponseEntity.ok(this.customerService.getCustomer(customerId).getCurrencies());
	}

	@Override
	@PostMapping("/{customerId}/currencies/{currency}")
	public ResponseEntity<CustomerDetailResponse> setCurrency(@PathVariable BigInteger customerId,
			@PathVariable SupportedCurrencyType currency) {
		customerService.setCurrency(customerId, currency);
		return ResponseEntity.ok(this.customerService.getCustomer(customerId));
	}

	@Override
	@DeleteMapping("/{customerId}/currencies/{currency}")
	public ResponseEntity<CustomerDetailResponse> deleteCurrency(@PathVariable BigInteger customerId,
			@PathVariable SupportedCurrencyType currency) {
		customerService.removeCurrency(customerId, currency);
		return ResponseEntity.ok(this.customerService.getCustomer(customerId));
	}

}
