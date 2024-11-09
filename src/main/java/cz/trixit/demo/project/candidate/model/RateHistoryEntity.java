package cz.trixit.demo.project.candidate.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rate_history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateHistoryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private SupportedCurrencyType currency;
	
	@Column(name = "value")
	private BigDecimal value;
	
	@Column(name = "value_for", nullable = false)
	private LocalDate date;
}
