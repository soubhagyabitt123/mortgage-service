**Mortgage Service Application**

**Overview**
The **Mortgage Service Application** is a RESTful backend service built using **Spring Boot**. It allows users to **retrieve interest rates** and **check mortgage eligibility** based on income, home value, and loan amount.

**Key Features**
✔ **Spring Boot 3** for backend API development  
✔ **H2 Database** for in-memory storage  
✔ **Spring Data JPA** for database operations  
✔ **Spring Security with JWT Authentication**  
✔ **Springdoc OpenAPI for API documentation (Swagger)**  
✔ **Unit tests using JUnit & Mockito**  

---

**📌 Prerequisites**
Ensure you have the following installed on your machine:  
- **JDK 17** or higher  
- **Maven 3+**  
- **Git** (for version control)  
- **Postman** (for API testing, optional)  

---

**📌 Setup and Installation**

**Clone the Repository**
git clone https://github.com/your-username/mortgage-service-app.git
cd mortgage-service-app

Build the Project
	mvn clean install
	
Run the Application
	mvn spring-boot:run
✔ The application will start at http://localhost:8082.

📌 API Endpoints
Interest Rate API
	HTTP Method		Endpoint				Description
	GET				/api/interest-rates		Retrieve a list of current mortgage rates
	
Mortgage Check API
	HTTP Method		Endpoint				Description
	POST			/api/mortgage-check		Check if a mortgage is feasible
	
📌 Example Request (POST /api/mortgage-check)
{
  "income": 50000,
  "maturityPeriod": 30,
  "loanValue": 200000,
  "homeValue": 250000
}
📌 Example Response
{
  "feasible": true,
  "monthlyCost": 1500.75
}

📌 Accessing API Documentation
Once the application is running, you can access Swagger UI: 🔗 Swagger UI: http://localhost:8082/swagger-ui.html

📌 Running Unit Tests
To run all unit tests, execute:
mvn clean test
✔ Generates test reports inside target/surefire-reports.

📌 Troubleshooting

Swagger UI Not Loading
Ensure the springdoc-openapi-ui dependency is in pom.xml
Restart the application and check logs for errors

H2 Console Not Working
Ensure H2 is enabled in application.yml
Open http://localhost:8082/h2-console
Use JDBC URL: jdbc:h2:mem:mortgageDB

Database Not Updating
Run:
mvn clean install
