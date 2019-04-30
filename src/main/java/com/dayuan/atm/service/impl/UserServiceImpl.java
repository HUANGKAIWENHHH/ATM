package com.dayuan.atm.service.impl;

import com.dayuan.atm.entity.User;
import com.dayuan.atm.entity.WxBind;
import com.dayuan.atm.exception.BizException;
import com.dayuan.atm.mapper.UserMapper;
import com.dayuan.atm.mapper.WxBindMapper;
import com.dayuan.atm.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxBindMapper wxBindMapper;

    @Override
    public void register(String username, String password, String confirmPassword) {

        //数据库可以通过唯一索引杜绝重复用户名，但此方法还是有存在必要，因为重复是小概率，此方法已经可以挡掉大部分，不要给数据库压力
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new BizException("用户名或密码不能为空");
        }

        if (!password.equals(confirmPassword)) {
            throw new BizException("两次密码输入不一致");
        }

        User user = userMapper.selectByUsername(username);
        if (user != null) {
            throw new BizException("用户名已存在");
        }

        user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setUserCreateTime(new Date());
        user.setUserModifyTime(new Date());
        user.setUserStatus(1);

        int rows = userMapper.insert(user);
        if (rows != 1) {
            throw new BizException("注册失败");
        }
    }

    @Override
    public User login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            throw new BizException("用户名或密码不能为空");
        }

        User user = userMapper.selectByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new BizException("用户名或密码错误");
        }

        return user;
    }

    @Override
    public void wxBind(int userId, String openId) {
        WxBind wxBind = wxBindMapper.getWxbind(openId);
        wxBind.setUserId(userId);

        int rows = wxBindMapper.updateByPrimaryKey(wxBind);
        if (rows != 1) {
            throw new BizException("绑定失败");
        }
    }

    @Override
    public WxBind getWxBind(String openId, String nickname, String headImgUrl, String unionId, String accessToken) {
        //插入信息或者更新头像、昵称等信息
        WxBind wxBind = wxBindMapper.getWxbind(openId);
        if (wxBind == null) {
            wxBind = new WxBind();
            wxBind.setUnionId(unionId);
            wxBind.setOpenId(openId);
            wxBind.setNickName(nickname);
            wxBind.setHeadImgUrl(headImgUrl);
            wxBind.setAccessToken(accessToken);

            wxBindMapper.insert(wxBind);
            return wxBind;
        } else {
            wxBind.setHeadImgUrl(headImgUrl);
            wxBind.setNickName(nickname);
            wxBind.setAccessToken(accessToken);

            wxBindMapper.updateByPrimaryKey(wxBind);
        }
        return wxBind;
    }

    @Override
    public User getUser(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
