package cz.trixit.demo.project.candidate.dao;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import cz.trixit.demo.project.candidate.model.RateHistoryEntity;

public interface RateHistoryRepository extends JpaRepository<RateHistoryEntity, BigInteger> {

	List<RateHistoryEntity> findByDate(LocalDate date);

	@Modifying
	@Query("delete from RateHistoryEntity")
	int deleteAllRates();
	
}
