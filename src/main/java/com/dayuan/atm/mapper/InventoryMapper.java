package com.dayuan.atm.mapper;

import com.dayuan.atm.entity.Inventory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    Inventory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inventory record);

    int updateByPrimaryKey(Inventory record);

    List<Inventory> listWaitVerify(@Param("status") Integer status);

    int modifyStatus(@Param("id") Integer id, @Param("newStatus") Integer newStatus, @Param("oldStatus") Integer oldStatus);
}