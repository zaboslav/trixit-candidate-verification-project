package cz.trixit.demo.project.candidate.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.trixit.demo.project.candidate.model.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, BigInteger> {

}
