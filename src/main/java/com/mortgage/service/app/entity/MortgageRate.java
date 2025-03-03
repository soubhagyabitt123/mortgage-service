package com.mortgage.service.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
public class MortgageRate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int maturityPeriod;
	private double interestRate;
	private LocalDateTime lastUpdate;

	public MortgageRate() {}

	public MortgageRate(int maturityPeriod, double interestRate, LocalDateTime lastUpdate) {
		this.maturityPeriod = maturityPeriod;
		this.interestRate = interestRate;
		this.lastUpdate = lastUpdate;
	}

	public MortgageRate(Long id, int maturityPeriod, double interestRate, LocalDateTime lastUpdate) {
		this.id = id;
		this.maturityPeriod = maturityPeriod;
		this.interestRate = interestRate;
		this.lastUpdate = lastUpdate;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public int getMaturityPeriod() { return maturityPeriod; }
	public void setMaturityPeriod(int maturityPeriod) { this.maturityPeriod = maturityPeriod; }
	public double getInterestRate() { return interestRate; }
	public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
	public LocalDateTime getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MortgageRate that = (MortgageRate) o;
		return maturityPeriod == that.maturityPeriod &&
				Double.compare(that.interestRate, interestRate) == 0 &&
				Objects.equals(id, that.id) &&
				Objects.equals(lastUpdate, that.lastUpdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, maturityPeriod, interestRate, lastUpdate);
	}

	@Override
	public String toString() {
		return "MortgageRateEntity{" +
				"id=" + id +
				", maturityPeriod=" + maturityPeriod +
				", interestRate=" + interestRate +
				", lastUpdate=" + lastUpdate +
				'}';
	}
}
