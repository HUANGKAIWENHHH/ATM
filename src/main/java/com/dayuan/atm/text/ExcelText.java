package com.dayuan.atm.text;

import com.dayuan.atm.entity.InventoryDetail;
import com.dayuan.atm.service.InventoryService;
import com.dayuan.atm.service.impl.InventoryServiceImpl;
import com.dayuan.atm.util.ExcelUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileInputStream;

public class ExcelText {

    public static void main(String[] args) throws Exception {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
        InventoryService inventoryService = context.getBean(InventoryService.class);

        inventoryService.addInventory(ExcelUtils.readExecl(new FileInputStream("/Users/huangkaiwen/Documents/svn/dayuan/dy12atm/doc/工资表.xls")));
    }
}
