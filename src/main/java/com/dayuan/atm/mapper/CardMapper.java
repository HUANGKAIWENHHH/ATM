package com.dayuan.atm.mapper;

import com.dayuan.atm.entity.Card;
import org.apache.ibatis.annotations.Param;

import java.io.InputStream;
import java.util.List;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

    //@Param 别名
    Card getCardbyNumber(@Param("cardNumber") String cardNumber);

    int updateBalance(@Param("cardId") Integer cardId, @Param("newbalance") Integer newbalance, @Param("oldbalance") Integer oldbalance);

    List<Card> listCardByUserId(@Param("userId") Integer userId);

    Card getCardById4Lock(@Param("cardId") Integer cardId);

}