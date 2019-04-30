package com.dayuan.atm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //格式化时间
    public static String dateToSting(Date flowCreateTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(flowCreateTime);
    }
}
