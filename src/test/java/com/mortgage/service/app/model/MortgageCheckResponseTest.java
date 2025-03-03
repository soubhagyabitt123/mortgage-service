package com.mortgage.service.app.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MortgageCheckResponseTest {

    @Test
    void testMortgageCheckResponseConstructor() {
        MortgageCheckResponse response = new MortgageCheckResponse(true, 1500.75);

        assertTrue(response.isFeasible());
        assertEquals(1500.75, response.getMonthlyCost(), 0.001); // Floating-point precision
    }

    @Test
    void testEqualsAndHashCode() {
        MortgageCheckResponse response1 = new MortgageCheckResponse(true, 1500.75);
        MortgageCheckResponse response2 = new MortgageCheckResponse(true, 1500.75);
        MortgageCheckResponse response3 = new MortgageCheckResponse(false, 1200.50);

        assertEquals(response1, response2);
        assertEquals(response1.hashCode(), response2.hashCode());
        assertNotEquals(response1, response3);
    }

    @Test
    void testToString() {
        MortgageCheckResponse response = new MortgageCheckResponse(false, 0.0);
        String expectedString = "MortgageCheckResponse{feasible=false, monthlyCost=0.0}";

        assertEquals(expectedString, response.toString());
    }
}
