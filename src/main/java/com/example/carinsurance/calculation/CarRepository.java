package com.example.carinsurance.calculation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Month;
import java.time.YearMonth;
import java.util.Map;

@Component
class CarRepository {
    private static final Map<String, Car> DATABASE = Map.of(
            "ABC 1234",
            new Car("ABC 1234",
                    YearMonth.of(2021, Month.MARCH), YearMonth.of(2021, Month.JUNE), BigDecimal.valueOf(40000), BigDecimal.valueOf(4500)),
            "DEF 567",
            new Car("DEF 567",
                    YearMonth.of(2021, Month.JANUARY), YearMonth.of(2021, Month.SEPTEMBER), BigDecimal.valueOf(12000), BigDecimal.valueOf(7730)),
            "GHI 8910",
            new Car("GHI 8910",
                    YearMonth.of(2020, Month.JANUARY), YearMonth.of(2021, Month.MAY), BigDecimal.valueOf(25000), BigDecimal.valueOf(7000)));

    public Car findByRegistrationNumber(String registrationNumber) {
        return DATABASE.get(registrationNumber);
    }
}
