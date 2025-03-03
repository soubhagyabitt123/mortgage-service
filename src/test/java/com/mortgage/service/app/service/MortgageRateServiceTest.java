package com.mortgage.service.app.service;

import com.mortgage.service.app.entity.MortgageRate;
import com.mortgage.service.app.exception.MortgageException;
import com.mortgage.service.app.model.MortgageCheckRequest;
import com.mortgage.service.app.model.MortgageCheckResponse;
import com.mortgage.service.app.repository.MortgageRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MortgageRateServiceTest {

    @Mock
    private MortgageRateRepository mortgageRateRepository;

    @InjectMocks
    private MortgageRateService mortgageRateService;

    private List<MortgageRate> mockRates;

    @BeforeEach
    void setUp() {
        mockRates = Arrays.asList(
                new MortgageRate(10, 5.0, LocalDateTime.now()),
                new MortgageRate(20, 4.5, LocalDateTime.now())
        );
    }

    @Test
    void getInterestRates_ShouldReturnAllRates() {
        when(mortgageRateRepository.findAll()).thenReturn(mockRates);

        List<MortgageRate> result = mortgageRateService.getInterestRates();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getMaturityPeriod());
        assertEquals(5.0, result.get(0).getInterestRate());
        verify(mortgageRateRepository, times(1)).findAll();
    }

    @Test
    void checkMortgage_ShouldReturnMonthlyPayment() throws Exception {
        when(mortgageRateRepository.findAll()).thenReturn(mockRates);

        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, 50000.0);
        MortgageCheckResponse response = mortgageRateService.checkMortgage(request);

        assertTrue(response.isFeasible());
        assertTrue(response.getMonthlyCost() > 0);
    }

    @Test
    void checkMortgage_ShouldThrowException_WhenLoanExceedsIncomeLimit() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 25000.0, 50000.0);

        Exception exception = assertThrows(MortgageException.class, () -> {
            mortgageRateService.checkMortgage(request);
        });

        assertEquals("Mortgage amount exceeds 4 times the income.", exception.getMessage());
    }

    @Test
    void checkMortgage_ShouldThrowException_WhenLoanExceedsHomeValue() {
        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 10, 20000.0, 15000.0);

        Exception exception = assertThrows(MortgageException.class, () -> {
            mortgageRateService.checkMortgage(request);
        });

        assertEquals("Mortgage amount exceeds home value.", exception.getMessage());
    }

    @Test
    void checkMortgage_ShouldThrowException_WhenNoRateFoundForMaturityPeriod() {
        when(mortgageRateRepository.findAll()).thenReturn(mockRates);

        MortgageCheckRequest request = new MortgageCheckRequest(5000.0, 15, 20000.0, 50000.0);

        Exception exception = assertThrows(MortgageException.class, () -> {
            mortgageRateService.checkMortgage(request);
        });

        assertEquals("No mortgage rate found for the given maturity period.", exception.getMessage());
    }
}
