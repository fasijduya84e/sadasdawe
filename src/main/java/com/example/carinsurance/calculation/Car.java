package com.example.carinsurance.calculation;

import lombok.Value;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

@Value
class Car {
    String registrationNumber;
    YearMonth insuranceDate;
    YearMonth damageReportedDate;
    BigDecimal valueOnInsuranceDate;
    BigDecimal damageValue;

    BigDecimal calculateCurrentValue() {
        long monthsBetweenDamageAndInsuranceStart = Math.abs(ChronoUnit.MONTHS.between(getDamageReportedDate(), getInsuranceDate()));
        return new BigDecimal("1.00")
                .subtract(
                        new BigDecimal("0.01").multiply(BigDecimal.valueOf(monthsBetweenDamageAndInsuranceStart)))
                .multiply(getValueOnInsuranceDate());
    }
}
