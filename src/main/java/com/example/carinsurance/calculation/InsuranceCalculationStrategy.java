package com.example.carinsurance.calculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

interface InsuranceCalculationStrategy {
    BigDecimal calculate(Car car);

    static InsuranceCalculationStrategy voidInsuranceStrategy() {
        return car -> applyDefaultScale(BigDecimal.ZERO);
    }

    static InsuranceCalculationStrategy defaultInsuranceStrategy() {
        return car -> applyDefaultScale(car.getDamageValue());
    }

    static InsuranceCalculationStrategy totalLossInsuranceStrategy() {
        return car -> applyDefaultScale(car.calculateCurrentValue().multiply(new BigDecimal("0.7")));
    }

    private static BigDecimal applyDefaultScale(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_EVEN);
    }
}
