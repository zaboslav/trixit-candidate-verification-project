package cz.trixit.demo.project.candidate.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cz.trixit.demo.project.candidate.model.WatchedCurrencyEntity;
import cz.trixit.demo.project.candidate.model.enums.SupportedCurrencyType;

public interface WatchedCurrencyRepository extends JpaRepository<WatchedCurrencyEntity, BigInteger> {

	
	@Modifying
	@Query("delete from WatchedCurrencyEntity e where e.customer.id = :customerId and currency = :currency")
	int deleteByCustomerIdAndCurrency(@Param("customerId") BigInteger customerId, @Param("currency") SupportedCurrencyType currency);

}
