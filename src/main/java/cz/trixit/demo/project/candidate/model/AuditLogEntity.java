package cz.trixit.demo.project.candidate.model;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "audit_log")
public class AuditLogEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name = "audit_type", nullable = false)
	private String type;
	
	@Column(name = "audit_detail_message", nullable = false)
	private String message;
	
	@Column(name = "failed", nullable = false)
	private Boolean failed;
	
	@Column(name = "error_detail")
	private String errorDetail;
	
	@CreationTimestamp
	@Column(name = "created")
	private OffsetDateTime created;

}
