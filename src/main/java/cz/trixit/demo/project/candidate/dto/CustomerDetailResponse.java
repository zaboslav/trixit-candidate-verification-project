package cz.trixit.demo.project.candidate.dto;

import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Data;

@Data
public class CustomerDetailResponse {
	
	private BigInteger id;
	
	private String customerName;
	
	private OffsetDateTime createdAt;

	private List<WatchedCurrencyResponse> currencies;

}
