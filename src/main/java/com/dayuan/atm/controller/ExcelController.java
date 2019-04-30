package com.dayuan.atm.controller;

import com.dayuan.atm.DTO.ResponseDTO;
import com.dayuan.atm.holder.CardExcelInfo;
import com.dayuan.atm.service.InventoryService;
import com.dayuan.atm.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController extends BaseController{

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/excel/uploadExcel", method = RequestMethod.POST)
    public void uploadExcel(HttpSession session, HttpServletResponse resp, @RequestParam("excelFile") MultipartFile file) {
        try {
            List<CardExcelInfo> list = ExcelUtils.readExecl(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/excel/listWaitVerify")
    @ResponseBody
    public ResponseDTO listWaitVerify() {
        return ResponseDTO.success(inventoryService.listWaitVerify());
    }

    @RequestMapping(value = "/excel/approvalInventory")
    @ResponseBody
    public ResponseDTO approvalInventory(int approvalstatus, int inventoryId) {
        return ResponseDTO.success(approvalInventory(approvalstatus, inventoryId));
    }
}
