package com.ortsevlised.consumerrservice;

public class CardRiskRequest {
    public String getCardNumber() {
        return cardNumber;
    }

    private final String cardNumber;

    public CardRiskRequest(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
