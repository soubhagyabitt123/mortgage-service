package com.mortgage.service.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MortgageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MortgageServiceApplication.class, args);
    }
}
