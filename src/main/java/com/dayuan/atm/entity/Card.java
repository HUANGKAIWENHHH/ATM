package com.dayuan.atm.entity;

import java.util.Date;

public class Card {
    private Integer id;

    private Integer userId;

    private String cardNumber;

    private String cardPassword;

    private Integer cardBalance;

    private Integer cardStatus;

    private Date cardCreateTime;

    private Date cardModifyTime;

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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public String getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String cardPassword) {
        this.cardPassword = cardPassword == null ? null : cardPassword.trim();
    }

    public Integer getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Integer cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Date getCardCreateTime() {
        return cardCreateTime;
    }

    public void setCardCreateTime(Date cardCreateTime) {
        this.cardCreateTime = cardCreateTime;
    }

    public Date getCardModifyTime() {
        return cardModifyTime;
    }

    public void setCardModifyTime(Date cardModifyTime) {
        this.cardModifyTime = cardModifyTime;
    }
}