package com.example.carinsurance.calculation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
class InsuranceCalculationService {
    private final CarRepository carRepository;
    private final StrategyFactory strategyFactory;

    BigDecimal calculate(String registrationNumber){
        var car = carRepository.findByRegistrationNumber(registrationNumber);
        var strategy = strategyFactory.chooseStrategy(car);
        return strategy.calculate(car);
    }
}
