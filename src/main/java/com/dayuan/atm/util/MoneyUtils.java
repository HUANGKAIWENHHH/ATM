package com.dayuan.atm.util;

import java.math.BigDecimal;

public class MoneyUtils {

    public static int mul(String amount) {
        //单位转换成分
        BigDecimal big1 = new BigDecimal(amount);
        BigDecimal big2 = new BigDecimal("100");

        //intValue()是把Integer对象类型变成int的基础数据类型
        return big1.multiply(big2).intValue();
    }

    public static String div(String amount) {
        BigDecimal big1 = new BigDecimal(amount);
        BigDecimal big2 = new BigDecimal("100");

        //银行家舍入法：ROUND_HALF_EVEN-向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入
        //转换成String
        //scale:保留2位小数
        return big1.divide(big2, 2, BigDecimal.ROUND_HALF_EVEN).toString();
    }

    //public static void main(String[] args) {
    //
    //    System.out.println(MoneyUtils.mul("0"));
    //}
}
