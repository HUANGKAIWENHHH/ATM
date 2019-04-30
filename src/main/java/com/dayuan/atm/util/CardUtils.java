package com.dayuan.atm.util;

import java.util.Random;

public class CardUtils {
    private static final int DEF_MAX_LENGTH = 6;

    public static String createCardNum() {
        return createCardNum(DEF_MAX_LENGTH);
    }

    public static String createCardNum(int DEF_MAX_LENGTH) {
        //对字符串进行修改,如拼接字符串
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("6222021310000");

        Random random = new Random();
        for (int i = 0; i < DEF_MAX_LENGTH; i++) {
            //String.valueOf 将对象转为String
            stringBuilder.append(String.valueOf(random.nextInt(10)));
        }
        return stringBuilder.toString();
    }

    //public static void main(String[] args) {
    //    System.out.println(CardUtils.createCardNum());
    //}
}
