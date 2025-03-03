package com.mortgage.service.app.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MortgageCheckRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validMortgageCheckRequest_ShouldPassValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertTrue(violations.isEmpty(), "There should be no validation errors for a valid request");
    }

    @Test
    void nullIncome_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(null, 10, 20000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Income is required", violations.iterator().next().getMessage());
    }

    @Test
    void incomeBelowMinimum_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(500.0, 10, 20000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Income must be at least 1000", violations.iterator().next().getMessage());
    }

    @Test
    void nullMaturityPeriod_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, null, 20000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Maturity period is required", violations.iterator().next().getMessage());
    }

    @Test
    void maturityPeriodBelowMinimum_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 0, 20000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Maturity period must be at least 1 year", violations.iterator().next().getMessage());
    }

    @Test
    void nullLoanValue_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, null, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Loan value is required", violations.iterator().next().getMessage());
    }

    @Test
    void loanValueBelowMinimum_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 5000.0, 50000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Loan value must be at least 10000", violations.iterator().next().getMessage());
    }

    @Test
    void nullHomeValue_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, null);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Home value is required", violations.iterator().next().getMessage());
    }

    @Test
    void homeValueBelowMinimum_ShouldFailValidation() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, 5000.0);
        Set<ConstraintViolation<MortgageCheckRequest>> violations = validator.validate(request);
        assertFalse(violations.isEmpty());
        assertEquals("Home value must be at least 10000", violations.iterator().next().getMessage());
    }
    
    @Test
    void testEquals() {
        MortgageCheckRequest request1 = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        MortgageCheckRequest request2 = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        MortgageCheckRequest request3 = new MortgageCheckRequest(6000.0, 15, 25000.0, 60000.0);

        assertEquals(request1, request2, "Equal objects should return true");
        assertNotEquals(request1, request3, "Different objects should return false");
    }

    @Test
    void testHashCode() {
        MortgageCheckRequest request1 = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        MortgageCheckRequest request2 = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);

        assertEquals(request1.hashCode(), request2.hashCode(), "Hash codes should be equal for equal objects");
    }

    @Test
    void testToString() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        String expectedString = "MortgageCheckRequest{income=5000.0, maturityPeriod=10, loanValue=20000.0, homeValue=50000.0}";

        assertEquals(expectedString, request.toString(), "toString() should return correct format");
    }
}
