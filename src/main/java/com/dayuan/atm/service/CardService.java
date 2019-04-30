package com.dayuan.atm.service;

import com.dayuan.atm.DTO.CardDTO;
import com.dayuan.atm.DTO.FlowDTO;
import com.dayuan.atm.entity.Card;
import com.dayuan.atm.holder.PageHolder;
import java.util.List;


public interface CardService {
    //开户
    void openAccount(String password, String confirmPassword, int userId);

    //存款
    void deposit(int cardId, String password, String amount, int userId);

    //取款
    void withdrawal(int cardId, String password, String amount, int userId);

    //转账
    void transfer(int cardOutId, String password, String amount, String cardInNum, int userId);

    //分页流水
    PageHolder listFlow(int currentPage, int cardId, String password, int userId);

    //查看银行卡
    List<CardDTO> listMyCard(int userId);

    //近10笔流水
    List<FlowDTO> listFlowTop10(int userId);

    Card getCard(int cardId);

}
