package com.dayuan.atm.DTO;

public class ResponseDTO {

    private int code;
    private String message;
    private Object data;

    private ResponseDTO(int code) {
        this.code = code;
    }

    private ResponseDTO(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    private ResponseDTO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseDTO toLogin() {
        return new ResponseDTO(5000, "重新登录");
    }

    public static ResponseDTO success() {
        return new ResponseDTO(1000);
    }

    public static ResponseDTO success(Object data) {
        return new ResponseDTO(1000, data);
    }

    public static ResponseDTO faild(String message) {
        return new ResponseDTO(9999, message);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }
}


