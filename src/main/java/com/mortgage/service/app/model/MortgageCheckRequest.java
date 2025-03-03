package com.mortgage.service.app.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class MortgageCheckRequest {

	@NotNull(message = "Income is required")
	@Min(value = 1000, message = "Income must be at least 1000")
	private Double income;

	@NotNull(message = "Maturity period is required")
	@Min(value = 1, message = "Maturity period must be at least 1 year")
	private Integer maturityPeriod;

	@NotNull(message = "Loan value is required")
	@Min(value = 10000, message = "Loan value must be at least 10000")
	private Double loanValue;

	@NotNull(message = "Home value is required")
	@Min(value = 10000, message = "Home value must be at least 10000")
	private Double homeValue;

	public MortgageCheckRequest() {}

	public MortgageCheckRequest(Double income, Integer maturityPeriod, Double loanValue, Double homeValue) {
		this.income = income;
		this.maturityPeriod = maturityPeriod;
		this.loanValue = loanValue;
		this.homeValue = homeValue;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Integer getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(Integer maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	public Double getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(Double loanValue) {
		this.loanValue = loanValue;
	}

	public Double getHomeValue() {
		return homeValue;
	}

	public void setHomeValue(Double homeValue) {
		this.homeValue = homeValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MortgageCheckRequest that = (MortgageCheckRequest) o;
		return Objects.equals(income, that.income) &&
				Objects.equals(maturityPeriod, that.maturityPeriod) &&
				Objects.equals(loanValue, that.loanValue) &&
				Objects.equals(homeValue, that.homeValue);
	}

	@Override
	public int hashCode() {
		return Objects.hash(income, maturityPeriod, loanValue, homeValue);
	}

	@Override
	public String toString() {
		return "MortgageCheckRequest{" +
				"income=" + income +
				", maturityPeriod=" + maturityPeriod +
				", loanValue=" + loanValue +
				", homeValue=" + homeValue +
				'}';
	}
}
