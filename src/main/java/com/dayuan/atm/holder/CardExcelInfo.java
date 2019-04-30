package com.dayuan.atm.holder;

public class CardExcelInfo {

    private String cardNunmber;

    private String amount;

    public CardExcelInfo() {

    }

    public CardExcelInfo(String cardNunmber, String amount) {
        this.cardNunmber = cardNunmber;
        this.amount = amount;
    }

    public String getCardNunmber() {
        return cardNunmber;
    }

    public void setCardNunmber(String cardNunmber) {
        this.cardNunmber = cardNunmber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
