package cz.trixit.demo.project.candidate.rest;

import org.springframework.http.ResponseEntity;

import cz.trixit.demo.project.candidate.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Rate History Controller")
public interface RateHistoryController {

	@Operation(summary = "Delete and load all rates from beging of the time.")
	@ApiResponse(responseCode = "200", description = "Rates successfully updated")
	@ApiResponse(responseCode = "400", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	ResponseEntity<Void> reloadRates();
}
