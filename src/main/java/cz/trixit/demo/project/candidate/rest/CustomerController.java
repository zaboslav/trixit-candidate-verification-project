package cz.trixit.demo.project.candidate.rest;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.ResponseEntity;

import cz.trixit.demo.project.candidate.dto.CustomerDetailResponse;
import cz.trixit.demo.project.candidate.dto.CustomerRequest;
import cz.trixit.demo.project.candidate.dto.CustomerResponse;
import cz.trixit.demo.project.candidate.dto.ErrorResponse;
import cz.trixit.demo.project.candidate.dto.WatchedCurrencyResponse;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Customer Controller")
public interface CustomerController {

	@Operation(summary = "Create a new customer")
	@ApiResponse(responseCode = "201", description = "Customer created")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<Void> createCustomer(@Valid CustomerRequest request);
	
	@Operation(summary = "Get all customers")
	@ApiResponse(responseCode = "200", description = "List of customer")
	ResponseEntity<List<CustomerResponse>> getCustomers();
	
	@Operation(summary = "Get customer detail")
	@ApiResponse(responseCode = "200", description = "Customer detail")
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<CustomerDetailResponse> getCustomer(BigInteger customerId);
	
	@Operation(summary = "Get customer currencies")
	@ApiResponse(responseCode = "200", description = "List of watched currencies")
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<List<WatchedCurrencyResponse>> getCustomerRates(BigInteger customerId);

	@Operation(summary = "Get add currency to customer")
	@ApiResponse(responseCode = "200", description = "Assign currency to watched currencies")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "409", description = "Already assigned", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<CustomerDetailResponse> setCurrency(BigInteger customerId, SupportedCurrencyType currency);
	
	@Operation(summary = "Get remove currency from customer")
	@ApiResponse(responseCode = "200", description = "Assign currency to watched currencies")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "404", description = "Currency does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<CustomerDetailResponse> deleteCurrency(BigInteger customerId, SupportedCurrencyType currency);

	@Operation(summary = "Update customer name")
	@ApiResponse(responseCode = "200", description = "Updated customer")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<CustomerDetailResponse> updateCustomer(BigInteger customerId, @Valid CustomerRequest request);

	@Operation(summary = "Delete customer")
	@ApiResponse(responseCode = "204", description = "Custome deleted")
	@ApiResponse(responseCode = "404", description = "Customer does not exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<Void> deleteCustomer(BigInteger customerId);
}
