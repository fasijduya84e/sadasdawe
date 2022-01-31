package com.example.carinsurance.calculation;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsuranceCalculationServiceTest {
    InsuranceCalculationService service = new InsuranceCalculationService(new CarRepository(), new StrategyFactory());

    @Test
    void shouldCalculateDefaultInsuranceCorrectly() {
        assertEquals(0, service.calculate("ABC 1234").compareTo(BigDecimal.valueOf(4500)));
    }

    @Test
    void shouldCalculateTotalLossInsuranceCorrectly() {
        assertEquals(0, service.calculate("DEF 567").compareTo(BigDecimal.valueOf(7728)));
    }

    @Test
    void shouldCalculateVoidInsuranceCorrectly() {
        assertEquals(0, service.calculate("GHI 8910").compareTo(BigDecimal.ZERO));
    }

}
