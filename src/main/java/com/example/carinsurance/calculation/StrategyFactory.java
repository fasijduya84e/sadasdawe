package com.example.carinsurance.calculation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Component
class StrategyFactory {
    private static final long INSURANCE_PERIOD_IN_YEARS = 1;
    private static final String TOTAL_LOSS_THRESHOLD = "0.7";

    InsuranceCalculationStrategy chooseStrategy(Car car) {
        if (insuranceExpired(car)) {
            return InsuranceCalculationStrategy.voidInsuranceStrategy();
        }
        if (isTotalLoss(car)) {
            return InsuranceCalculationStrategy.totalLossInsuranceStrategy();
        }
        return InsuranceCalculationStrategy.defaultInsuranceStrategy();
    }

    private boolean insuranceExpired(Car car) {
        YearMonth insuranceExpirationDate = car.getInsuranceDate().plus(INSURANCE_PERIOD_IN_YEARS, ChronoUnit.YEARS);
        return !insuranceExpirationDate
                .isAfter(car.getDamageReportedDate());
    }

    private boolean isTotalLoss(Car car) {
        return car.getDamageValue().divide(car.calculateCurrentValue(), RoundingMode.HALF_EVEN).compareTo(new BigDecimal(TOTAL_LOSS_THRESHOLD)) > 0;
    }
}
