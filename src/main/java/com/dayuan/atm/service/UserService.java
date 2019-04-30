package com.dayuan.atm.service;

import com.dayuan.atm.entity.User;
import com.dayuan.atm.entity.WxBind;

public interface UserService {

    void register(String username, String password, String confirmPassword);

    User login(String username, String password);

    void wxBind(int userId, String openId);

    WxBind getWxBind(String openId, String nickname, String headImgUrl, String unionId, String accessToken);

    User getUser(int userId);
}
