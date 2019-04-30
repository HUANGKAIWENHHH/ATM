package com.dayuan.atm.mapper;

import com.dayuan.atm.entity.Flow;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface FlowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    //pageSize:返回行数 offset:索引，从第几条开始返回
    List<Flow> listFlow(@Param("cardId") Integer cardId,
                        @Param("offset") Integer offset,
                        @Param("pageSize") Integer pageSize);

    int countFlow(@Param("cardId") Integer cardId);

    List<Flow> listFlowTop10(@Param("userId") Integer userId);
}