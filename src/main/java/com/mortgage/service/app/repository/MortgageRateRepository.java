package com.mortgage.service.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortgage.service.app.entity.MortgageRate;

@Repository
public interface MortgageRateRepository extends JpaRepository<MortgageRate, Long> {
	Optional<MortgageRate> findByMaturityPeriod(int maturityPeriod);
}