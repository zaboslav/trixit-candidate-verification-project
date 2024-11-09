package cz.trixit.demo.project.candidate.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConversionRequest {

	@NotNull
	@Schema(description = "Amount to covert", nullable = false)
	private BigDecimal amount;

	@NotNull
	@Schema(description = "Currency of amoumt", nullable = false)
	private SupportedCurrencyType from;

	@NotNull
	@Schema(description = "Target currency", nullable = false)
	private SupportedCurrencyType to;

	@Schema(description = "Optional timestamp of conversion", nullable = false)
	private OffsetDateTime conversionAt;
}
