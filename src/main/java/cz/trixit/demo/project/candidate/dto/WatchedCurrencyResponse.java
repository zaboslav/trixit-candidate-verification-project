package cz.trixit.demo.project.candidate.dto;

import java.time.OffsetDateTime;

import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import lombok.Data;

@Data
public class WatchedCurrencyResponse {
	
	private SupportedCurrencyType currency;
	
	private OffsetDateTime watchedFrom;
}
