package com.dayuan.atm.mapper;

import com.dayuan.atm.entity.InventoryDetail;

public interface InventoryDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryDetail record);

    int insertSelective(InventoryDetail record);

    InventoryDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InventoryDetail record);

    int updateByPrimaryKey(InventoryDetail record);
}