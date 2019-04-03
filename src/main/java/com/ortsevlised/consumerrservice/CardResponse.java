package com.ortsevlised.consumerrservice;

public class CardResponse {

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CardResponse(Status status) {
        this.status=status;
    }
}
