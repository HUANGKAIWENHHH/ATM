package com.dayuan.atm.service;

import com.dayuan.atm.entity.Inventory;
import com.dayuan.atm.holder.CardExcelInfo;

import java.util.List;

public interface InventoryService {
    //持久化清单
    void addInventory(List<CardExcelInfo> list);

    //列出等待审批清单
    List<Inventory> listWaitVerify();

    //审批清单
    void approvalInventory(int approvalstatus, int inventoryId, int userId);

}
