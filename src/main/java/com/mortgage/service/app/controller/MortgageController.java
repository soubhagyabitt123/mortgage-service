package com.mortgage.service.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.service.app.entity.MortgageRate;
import com.mortgage.service.app.model.MortgageCheckRequest;
import com.mortgage.service.app.model.MortgageCheckResponse;
import com.mortgage.service.app.service.MortgageRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MortgageController {

	@Autowired
	private MortgageRateService mortgageService;

	@Operation(summary = "Get the interest rates", description = "Get a list of current interest rates.")
    @ApiResponse(responseCode = "200", description = "List of interest rates retrieved successfully")
    @GetMapping("/interest-rates")
    public List<MortgageRate> getInterestRates() {
        return mortgageService.getInterestRates();
    }

	@Operation(summary = "Calculate for a mortgage check", description = "Calculate for a mortgage check based on provided inputs.")
    @ApiResponse(responseCode = "200", description = "Mortgage rate checked successfully")
    @PostMapping("/mortgage-check")
    public ResponseEntity<MortgageCheckResponse> checkMortgage(@Valid @RequestBody MortgageCheckRequest request) throws Exception {
		MortgageCheckResponse response = mortgageService.checkMortgage(request);
	    return ResponseEntity.ok(response);
    }
    
}
