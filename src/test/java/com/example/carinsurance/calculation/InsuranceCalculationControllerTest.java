package com.example.carinsurance.calculation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = InsuranceCalculationController.class)
class InsuranceCalculationControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private InsuranceCalculationService service;

    @Test
    void shouldReturn200() throws Exception {
        String registrationNumber = "AB12345";
        BigDecimal insuranceValue = BigDecimal.TEN.setScale(2, RoundingMode.HALF_EVEN);
        CalculateInsuranceRequest request = new CalculateInsuranceRequest();
        request.setRegistrationNumber(registrationNumber);
        when(service.calculate(registrationNumber)).thenReturn(insuranceValue);

        CalculateInsuranceResponse expectedResponse = new CalculateInsuranceResponse(registrationNumber, insuranceValue);
        mockMvc.perform(
                post("/calculateInsurance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(OBJECT_MAPPER.writeValueAsString(expectedResponse)));
    }
}
