package com.ortsevlised.consumerrservice;

public class CardRequest {
    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    private CardType cardType;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
                this.cardNumber = cardNumber;
    }

    private String cardNumber;

}
