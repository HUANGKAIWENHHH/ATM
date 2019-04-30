package com.dayuan.atm.exception;

public class BizException extends RuntimeException {

    public String message;

    public BizException(String message) {
        super(message);
        this.message = message;
    }

    public String getMenssage() {
        return message;
    }

    public void setMenssage(String menssage) {
        this.message = menssage;
    }
}
