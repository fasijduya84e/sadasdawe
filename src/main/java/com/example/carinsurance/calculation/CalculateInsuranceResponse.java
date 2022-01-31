package com.example.carinsurance.calculation;

import lombok.Value;

import java.math.BigDecimal;

@Value
class CalculateInsuranceResponse {
    String registrationNumber;
    BigDecimal insuranceValue;
}
