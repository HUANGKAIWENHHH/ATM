package com.dayuan.atm.DTO;

import java.util.Date;

public class FlowDTO {

    private String cardNumber;

    private String amount;

    private String flowDesc;

    private String flowCreateTime;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc;
    }

    public String getFlowCreateTime() {
        return flowCreateTime;
    }

    public void setFlowCreateTime(String flowCreateTime) {
        this.flowCreateTime = flowCreateTime;
    }
}
