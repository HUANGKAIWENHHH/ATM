package com.dayuan.atm.entity;

import java.util.Date;

public class Flow {
    private Integer id;

    private Integer userId;

    private Integer cardId;

    private String cardNumber;

    private Integer amount;

    private Integer flowType;

    private String flowDesc;

    private Date flowCreateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFlowType() {
        return flowType;
    }

    public void setFlowType(Integer flowType) {
        this.flowType = flowType;
    }

    public String getFlowDesc() {
        return flowDesc;
    }

    public void setFlowDesc(String flowDesc) {
        this.flowDesc = flowDesc == null ? null : flowDesc.trim();
    }

    public Date getFlowCreateTime() {
        return flowCreateTime;
    }

    public void setFlowCreateTime(Date flowCreateTime) {
        this.flowCreateTime = flowCreateTime;
    }
}