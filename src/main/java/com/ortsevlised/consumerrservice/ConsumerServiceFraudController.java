package com.ortsevlised.consumerrservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerServiceFraudController {

    private final RestTemplate restTemplate;

    public ConsumerServiceFraudController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/fraud-detector")
    public CardResponse checkCardRisk(CardRequest cardRequest) {
        final String cardNumber = cardRequest.getCardNumber();

        CardRiskResponse cardRiskResponse = restTemplate.postForObject("http://localhost:8080/risk-level", new CardRiskRequest(cardNumber), CardRiskResponse.class);

        if (cardRiskResponse.getRisk().equals(Risk.HIGH)) {
            return new CardResponse(Status.BLOCKED);
        } else {
            return new CardResponse(Status.VALID);
        }
    }
}
