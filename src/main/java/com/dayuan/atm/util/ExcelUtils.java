package com.dayuan.atm.util;

import com.dayuan.atm.holder.CardExcelInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<CardExcelInfo> readExecl(InputStream inputStream) {
        List<CardExcelInfo> list = new ArrayList<>();

        try {
            // 打开指定位置的Excel文件
            Workbook workbook = new HSSFWorkbook(inputStream);
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);

            //遍历行
            for (Row row : sheet) {
                //第一行是表名，跳出不读取
                if (row.getRowNum() == 0) {
                    continue;
                }
                //创建存储对象，并将对象放入list数组中
                CardExcelInfo cardExcelInfo = new CardExcelInfo();
                list.add(cardExcelInfo);

                //遍历单元格
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        //单元格如果前边有0，excel默认会把该单元格改成string格式，所以不用转换，否则0就丢失了
                        if (cell.getCellType() == CellType.STRING) {
                            cardExcelInfo.setCardNunmber(cell.toString());
                        }
                        //单元格若是数字类型，通过转换去掉.0，并改成String格式
                        if (cell.getCellType() == CellType.NUMERIC) {
                            //数字类型的String字符串转换为浮点数,再强转成int
                            int carNum = (int)Double.parseDouble(cell.toString());
                            //int转换成String
                            cardExcelInfo.setCardNunmber(String.valueOf(carNum));
                        }
                    }

                    if (cell.getColumnIndex() == 1) {
                        //double amount = Double.parseDouble(cell.toString());
                        //cardExcelInfo.setAmount(String.valueOf(amount));
                        cardExcelInfo.setAmount(cell.toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    //public static void main(String[] args) {
    //    try {
    //        FileInputStream fileInputStream = new FileInputStream("/Users/huangkaiwen/Documents/svn/dayuan/dy12atm/doc/工资表.xls");
    //
    //        List<CardExcelInfo> list = readExecl(fileInputStream);
    //        for (CardExcelInfo cardExcelInfo : list) {
    //            System.out.println(cardExcelInfo.getCardNunmber() + "=" + cardExcelInfo.getAmount());
    //        }
    //    } catch (FileNotFoundException e) {
    //        e.printStackTrace();
    //    }
    //
    //}
}
