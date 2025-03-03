package com.mortgage.service.app.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MortgageRateTest {

    @Test
    void testMortgageRateEntityConstructor() {
        LocalDateTime now = LocalDateTime.now();
        MortgageRate entity = new MortgageRate(1L, 15, 3.5, now);

        assertEquals(1L, entity.getId());
        assertEquals(15, entity.getMaturityPeriod());
        assertEquals(3.5, entity.getInterestRate());
        assertEquals(now, entity.getLastUpdate());
    }

    @Test
    void testMortgageRateEntitySetters() {
        MortgageRate entity = new MortgageRate();
        entity.setId(2L);
        entity.setMaturityPeriod(30);
        entity.setInterestRate(4.2);
        entity.setLastUpdate(LocalDateTime.of(2025, 2, 27, 10, 0));

        assertEquals(2L, entity.getId());
        assertEquals(30, entity.getMaturityPeriod());
        assertEquals(4.2, entity.getInterestRate());
        assertEquals(LocalDateTime.of(2025, 2, 27, 10, 0), entity.getLastUpdate());
    }

    @Test
    void testEqualsAndHashCode() {
        LocalDateTime now = LocalDateTime.now();
        MortgageRate entity1 = new MortgageRate(1L, 15, 3.5, now);
        MortgageRate entity2 = new MortgageRate(1L, 15, 3.5, now);
        MortgageRate entity3 = new MortgageRate(2L, 20, 4.0, now);

        assertEquals(entity1, entity2); // Should pass now
        assertNotEquals(entity1, entity3);
        assertEquals(entity1.hashCode(), entity2.hashCode());
    }

    @Test
    void testToString() {
        MortgageRate entity = new MortgageRate(1L, 15, 3.5, LocalDateTime.of(2025, 2, 27, 10, 0));
        String expectedString = "MortgageRateEntity{id=1, maturityPeriod=15, interestRate=3.5, lastUpdate=2025-02-27T10:00}";

        assertEquals(expectedString, entity.toString());
    }
}
