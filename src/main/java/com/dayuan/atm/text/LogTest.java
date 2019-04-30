package com.dayuan.atm.text;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    public static void main(String[] args) {

        //当前logger负责哪个类
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        logger.debug("Hello world.");
    }
}
