package com.mortgage.service.app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Employee Management API", version = "v1", description = "API for managing employees, projects and roles"))
public class MortgageServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MortgageServiceApplication.class, args);
    }
}