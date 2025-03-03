package com.mortgage.service.app.service;

import com.mortgage.service.app.entity.MortgageRate;
import com.mortgage.service.app.exception.MortgageException;
import com.mortgage.service.app.model.MortgageCheckRequest;
import com.mortgage.service.app.model.MortgageCheckResponse;
import com.mortgage.service.app.repository.MortgageRateRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import jakarta.annotation.PostConstruct;

@Service
public class MortgageRateService {

	private final MortgageRateRepository mortgageRateRepository;

    public MortgageRateService(MortgageRateRepository repository) {
        this.mortgageRateRepository = repository;
    }
	
    //Insert some default values in DB inorder to retrieve it.
    @PostConstruct
    public void insertDefaultMortgageRates() {
        if (mortgageRateRepository.count() == 0) {  // Insert only if DB is empty
            mortgageRateRepository.saveAll(List.of(
                new MortgageRate(null, 15, 3.5, LocalDateTime.now()),
                new MortgageRate(null, 30, 4.2, LocalDateTime.now())
            ));
        }
    }
	
    /**
     * 
     * @return List of all current interest rates
     */
    public List<MortgageRate> getInterestRates() {
        return mortgageRateRepository.findAll()
                .stream()
                .map(rate -> new MortgageRate(rate.getMaturityPeriod(), rate.getInterestRate(), rate.getLastUpdate()))
                .collect(Collectors.toList());
    }

    /**
     * Calculate for a mortgage check.The mortgage check return if the mortgage is feasible (boolean) and the monthly costs 
	 * (Amount) of the mortgage.
	 * 
     * @param request MortgageCheckRequest
     * @return mortgage is feasible (boolean) and monthly costs(Amount)
     * @throws Exception
     */
    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request) throws Exception {
        double maxLoan = request.getIncome() * 4;
        if (request.getLoanValue() > maxLoan ) {
            throw new MortgageException("Mortgage amount exceeds 4 times the income.");
        }
        if (request.getLoanValue() > request.getHomeValue()) {
            throw new MortgageException("Mortgage amount exceeds home value.");
        }
        MortgageRate rate = getInterestRates().stream()
                .filter(r -> r.getMaturityPeriod() == request.getMaturityPeriod())
                .findFirst()
                .orElseThrow(() -> new MortgageException("No mortgage rate found for the given maturity period."));

        double annualInterestRate = rate.getInterestRate();
        int years = request.getMaturityPeriod();
        double loanAmount = request.getLoanValue();

        double monthlyInterestRate = annualInterestRate / 100 / 12;
        int totalMonths = years * 12;

        double monthlyPayment = (loanAmount * monthlyInterestRate) /
                (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));

        return new MortgageCheckResponse(true, monthlyPayment);
    }
}
