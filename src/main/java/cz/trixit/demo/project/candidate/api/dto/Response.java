package cz.trixit.demo.project.candidate.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import lombok.Data;

@Data
public class Response {
	private LocalDate date;
	private String base;
	private Map<String, BigDecimal> rates;
}
