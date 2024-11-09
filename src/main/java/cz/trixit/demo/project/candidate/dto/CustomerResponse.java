package cz.trixit.demo.project.candidate.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class CustomerResponse {
	
	private Long id;
	
	private String customerName;
	
	private OffsetDateTime createdAt;

}
