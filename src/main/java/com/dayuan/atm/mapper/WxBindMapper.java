package com.dayuan.atm.mapper;

import com.dayuan.atm.entity.WxBind;
import org.apache.ibatis.annotations.Param;

public interface WxBindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WxBind record);

    int insertSelective(WxBind record);

    WxBind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WxBind record);

    int updateByPrimaryKey(WxBind record);

    WxBind getWxbind(@Param("openId") String openId);
}