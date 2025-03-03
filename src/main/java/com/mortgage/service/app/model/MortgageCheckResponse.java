package com.mortgage.service.app.model;

import java.util.Objects;

public class MortgageCheckResponse {

	private boolean feasible;
	private double monthlyCost;

	public MortgageCheckResponse() {
		super();
	}

	public MortgageCheckResponse(boolean feasible, double monthlyCost) {
		this.feasible = feasible;
		this.monthlyCost = monthlyCost;
	}

	public boolean isFeasible() {
		return feasible;
	}

	public void setFeasible(boolean feasible) {
		this.feasible = feasible;
	}

	public double getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(double monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MortgageCheckResponse that = (MortgageCheckResponse) o;
		return feasible == that.feasible &&
				Double.compare(that.monthlyCost, monthlyCost) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(feasible, monthlyCost);
	}

	@Override
	public String toString() {
		return "MortgageCheckResponse{" +
				"feasible=" + feasible +
				", monthlyCost=" + monthlyCost +
				'}';
	}
}
