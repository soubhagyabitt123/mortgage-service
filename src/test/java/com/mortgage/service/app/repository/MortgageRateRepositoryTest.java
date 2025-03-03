//package com.mortgage.service.app.repository;
//
//import com.mortgage.service.app.entity.MortgageRate;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import java.time.LocalDateTime;
//import java.util.Optional;
//import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Uses the real database setup
//class MortgageRateRepositoryTest {
//
//    @Autowired
//    private MortgageRateRepository mortgageRateRepository;
//
//    @Test
//    void testSaveAndFindById() {
//        MortgageRate entity = new MortgageRate(15, 3.5, LocalDateTime.now());
//        MortgageRate savedEntity = mortgageRateRepository.save(entity);
//
//        Optional<MortgageRate> foundEntity = mortgageRateRepository.findById(savedEntity.getId());
//
//        assertTrue(foundEntity.isPresent());
//        assertEquals(15, foundEntity.get().getMaturityPeriod());
//        assertEquals(3.5, foundEntity.get().getInterestRate());
//    }
//
//    @Test
//    void testFindByMaturityPeriod() {
//        MortgageRate entity = new MortgageRate(30, 4.2, LocalDateTime.now());
//        mortgageRateRepository.save(entity);
//
//        Optional<MortgageRate> foundEntity = mortgageRateRepository.findByMaturityPeriod(30);
//
//        assertTrue(foundEntity.isPresent());
//        assertEquals(30, foundEntity.get().getMaturityPeriod());
//        assertEquals(4.2, foundEntity.get().getInterestRate());
//    }
//
//    @Test
//    void testFindByMaturityPeriod_NotFound() {
//        Optional<MortgageRate> foundEntity = mortgageRateRepository.findByMaturityPeriod(100);
//        assertFalse(foundEntity.isPresent());
//    }
//
//    @Test
//    void testDeleteById() {
//        MortgageRate entity = new MortgageRate(10, 3.8, LocalDateTime.now());
//        MortgageRate savedEntity = mortgageRateRepository.save(entity);
//
//        mortgageRateRepository.deleteById(savedEntity.getId());
//
//        Optional<MortgageRate> foundEntity = mortgageRateRepository.findById(savedEntity.getId());
//        assertFalse(foundEntity.isPresent());
//    }
//}
