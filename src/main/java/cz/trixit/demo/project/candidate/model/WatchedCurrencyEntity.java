package cz.trixit.demo.project.candidate.model;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "watched_currency")
@Data
public class WatchedCurrencyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;

	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private SupportedCurrencyType currency;
	
	@CreationTimestamp
	@Column(name = "created")
	private OffsetDateTime created;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
}
