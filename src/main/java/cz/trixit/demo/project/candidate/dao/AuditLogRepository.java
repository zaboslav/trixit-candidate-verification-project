package cz.trixit.demo.project.candidate.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.trixit.demo.project.candidate.model.AuditLogEntity;

public interface AuditLogRepository extends JpaRepository<AuditLogEntity, BigInteger> {

}
