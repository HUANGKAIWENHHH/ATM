package com.dayuan.atm.service;

import com.dayuan.atm.DTO.CardDTO;

import java.util.List;

public interface RedicService {

    void cacheCard(List<CardDTO> cardDTOS, int userId);

    List<CardDTO> getCard(int userId);

    void expire(String key , int second);

    String getKey(String module, String function, String parm);
}
