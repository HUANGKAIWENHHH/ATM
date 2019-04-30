package com.dayuan.atm.service.impl;

import com.dayuan.atm.entity.Inventory;
import com.dayuan.atm.entity.InventoryDetail;
import com.dayuan.atm.exception.BizException;
import com.dayuan.atm.holder.CardExcelInfo;
import com.dayuan.atm.mapper.CardMapper;
import com.dayuan.atm.mapper.InventoryDetailMapper;
import com.dayuan.atm.mapper.InventoryMapper;
import com.dayuan.atm.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private CardMapper cardMapper;

    @Override
    @Transactional
    public void addInventory(List<CardExcelInfo> list) {
        Inventory inventory = new Inventory();
        inventory.setCreateTime(new Date());
        inventory.setInventoryDesc("");
        inventory.setInventoryName("");
        inventory.setModifyTime(new Date());
        inventory.setStatus(0);
        inventory.setOperator("");

        int rows = inventoryMapper.insert(inventory);
        if (rows != 1) {
            throw new BizException("上传失败");
        }

        for (CardExcelInfo cardExcelInfo : list) {
            //校验卡号
            if (cardMapper.getCardbyNumber(cardExcelInfo.getCardNunmber()) == null) {
                throw new BizException(cardExcelInfo.getCardNunmber() + "卡号不存在");
            }

            if (inventory.getStatus() != 0) {
                throw new BizException("请确认清单状态");
            }

            InventoryDetail inventoryDetail = new InventoryDetail();
            inventoryDetail.setAmount(cardExcelInfo.getAmount());
            inventoryDetail.setCardNumber(cardExcelInfo.getCardNunmber());
            inventoryDetail.setCreateTime(new Date());
            inventoryDetail.setModifyTime(new Date());
            inventoryDetail.setStatus(0);
            //此时ID不为null，因为在mapper里，insert语句嵌套了select语句，查询并返回最后的一个id
            //jdbc通过重载，在preparestatement里添加Statement.RETURN_GENERATED_KEYS,然后通过getGeneratedKeys()获取ID
            inventoryDetail.setInventoryId(inventory.getId());

            inventoryDetailMapper.insert(inventoryDetail);
        }
    }

    @Override
    public List<Inventory> listWaitVerify() {
        return inventoryMapper.listWaitVerify(0);
    }

    @Override
    public void approvalInventory(int approvalstatus, int inventoryId, int userId) {
        Inventory inventory = inventoryMapper.selectByPrimaryKey(inventoryId);
        if (inventory == null) {
            throw new BizException("清单不存在");
        }

        if (inventory.getStatus() != 0) {
            throw new BizException("清单状态出错");
        }

        //防止相同权限重复操作，此处加了乐观锁
        int rows = inventoryMapper.modifyStatus(inventoryId, 1, inventory.getStatus());
        if (rows != 1) {
            throw new BizException("操作失败");
        }
    }
}
