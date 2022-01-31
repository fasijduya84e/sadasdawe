package com.example.carinsurance.calculation;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/calculateInsurance")
@AllArgsConstructor
class InsuranceCalculationController {
    private final InsuranceCalculationService insuranceCalculationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CalculateInsuranceResponse> calculateInsurance(@RequestBody CalculateInsuranceRequest request) {
        String registrationNumber = request.getRegistrationNumber();
        BigDecimal insuranceValue = insuranceCalculationService.calculate(registrationNumber);
        return ResponseEntity.ok(new CalculateInsuranceResponse(registrationNumber, insuranceValue));
    }
}
