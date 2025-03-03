package com.mortgage.service.app.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mortgage.service.app.entity.MortgageRate;
import com.mortgage.service.app.model.MortgageCheckRequest;
import com.mortgage.service.app.model.MortgageCheckResponse;
import com.mortgage.service.app.service.MortgageRateService;

@ExtendWith(MockitoExtension.class)
class MortgageControllerTest {

    @Mock
    private MortgageRateService mortgageService;

    @InjectMocks
    private MortgageController mortgageController;

    private MockMvc mockMvc;
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(mortgageController).build();
    }

    @Test
    void testGetInterestRates() throws Exception {
        when(mortgageService.getInterestRates()).thenReturn(List.of(
                new MortgageRate(1L, 15, 3.5, LocalDateTime.now()),
                new MortgageRate(2L, 30, 4.2, LocalDateTime.now())
        ));

        mockMvc = MockMvcBuilders.standaloneSetup(mortgageController).build();

        mockMvc.perform(get("/api/interest-rates")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].maturityPeriod").value(15));
    }
    
    @Test
    void testCheckMortgage() throws Exception {
        // Arrange: Create a request with valid values
        MortgageCheckRequest request = new MortgageCheckRequest(50000.0, 30, 200000.0, 250000.0);
        MortgageCheckResponse response = new MortgageCheckResponse(true, 1500.75);

        when(mortgageService.checkMortgage(any(MortgageCheckRequest.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/mortgage-check")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))) // Convert to JSON
                .andExpect(status().isOk()) // Expect 200 OK
                .andExpect(jsonPath("$.feasible").value(true))
                .andExpect(jsonPath("$.monthlyCost").value(1500.75));
    }
}
