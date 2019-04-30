package com.dayuan.atm.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dayuan.atm.DTO.ResponseDTO;
import com.dayuan.atm.entity.User;
import com.dayuan.atm.entity.WxBind;
//import com.dayuan.atm.holder.AccessTokenInfo;
//import com.dayuan.atm.holder.WxBindHolder;
//import com.dayuan.atm.holder.WxUserInfo;
import com.dayuan.atm.service.UserService;
import com.dayuan.atm.util.HttpUtils;
import com.dayuan.authen.wechat.AccessTokenInfo;
import com.dayuan.authen.wechat.WxBindHolder;
import com.dayuan.authen.wechat.WxUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Controller
public class WxController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private WxBindHolder wxBindHolder;

    //请求CODE
    @RequestMapping(value = "/wx/getQrconnectUrl")
    @ResponseBody
    public ResponseDTO getQrconnectUrl() {
        return ResponseDTO.success(wxBindHolder.createQrconnectUrl());
    }

    //获取acessToken传统方式-废弃
    @RequestMapping(value = "/wx/callBack")
    @ResponseBody
    public ResponseDTO callBack(String code) {
        //日志输出
        //logger.info(code);
        //日志输出，{}占位符，替代了+,相当于StringBuilder的append，推荐使用
        logger.info("code={}",code);

        //获取acessToken
        String acessTokenUrl = "http://payhub.dayuanit.com/weixin/sns/oauth2/access_token.do?" +
                "appid=2019042314385327065&" +
                "secret=14249841908579657792146357981369&" +
                "code=" + code + "&grant_type=authorization_code";

        //java发送Http
        BufferedReader bufferedReader = null;
        String result = "";
        try {
            URL realUrl = new URL(acessTokenUrl);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            //定义 BufferedReader输入流来读取URL的响应
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 建立实际的连接
            connection.connect();

            String msg = null;
            while (null != (msg = bufferedReader.readLine())) {
                result += msg;
            }
            logger.info(result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ResponseDTO.success();
    }

    //使用Apache的HttpClient发送请求、接收响应  - acessToken
    @RequestMapping(value = "/wx/callBack2")
    public ModelAndView callBack2(String code, HttpSession session) {
        ////获取acessToken
        //String acessTokenUrl = "http://payhub.dayuanit.com/weixin/sns/oauth2/access_token.do?" +
        //        "appid=2019042314385327065&" +
        //        "secret=14249841908579657792146357981369&" +
        //        "code=" + code + "&grant_type=authorization_code";
        //
        ////获取json串
        //String result = HttpUtils.get(acessTokenUrl);
        ////JSON转对象
        //JSONObject jsonObject =  JSON.parseObject(result);
        ////获取access_token、openid值
        //String accessToken = jsonObject.getString("access_token");
        //String openId = jsonObject.getString("openid");
        //
        ////获取用户信息
        //String userInfoURL = "http://payhub.dayuanit.com/weixin/sns/userinfo.do?" +
        //        "access_token=" + accessToken + "&openid=" + openId;
        //result = HttpUtils.get(userInfoURL);
        //
        ////判断是否已经绑定，如果已经绑定则去个人中心页面，反之去绑定页面
        //WxBind wxBind = userService.getWxbind(openId);
        //ModelAndView modelAndView = new ModelAndView();
        //
        //if (wxBind == null) {
        //    //重定向，防止重复提交，导致code重复使用而报错，微信头像等信息丢失
        //    modelAndView.setViewName("redirect:/wx/toWxBind");
        //
        //    JSONObject userInfoURLJSON = JSON.parseObject(result);
        //    session.setAttribute("headImgUrl", userInfoURLJSON.getString("headimgurl"));
        //    session.setAttribute("openId", userInfoURLJSON.getString("openid"));
        //    session.setAttribute("nickname", userInfoURLJSON.getString("nickname"));
        //    session.setAttribute("accessToken", jsonObject.getString("access_token"));
        //    session.setAttribute("unionId", userInfoURLJSON.getString("unionid"));
        //
        //    return modelAndView;
        //} else {
        //    //绑定过，就要去个人中心页面，去之前要查出个人信息，并传进session
        //    User user = userService.getUser(wxBind.getUserId());
        //    session.setAttribute("loginUser", user);
        //    modelAndView.setViewName("redirect:/user/toHome");
        //
        //    return modelAndView;
        //}

        //获取accessToken
        AccessTokenInfo accessTokenInfo = wxBindHolder.getAccessToken(code);
        //获取用户信息
        WxUserInfo wxUserInfo = wxBindHolder.getWxUserInfo(accessTokenInfo.getAccessToken(), accessTokenInfo.getOpenid());

        //持久化，除了userid以外的信息，因为此时不知道userid
        WxBind wxBind = userService.getWxBind(wxUserInfo.getOpenid(), wxUserInfo.getNickname(), wxUserInfo.getHeadimgurl(),
                wxUserInfo.getUnionid(), accessTokenInfo.getAccessToken());
        ModelAndView modelAndView = new ModelAndView();

        //前边持久化并没有绑定userid，所以通过userid判断是否已经绑定，
        if (wxBind.getUserId() == null) {
            //重定向，防止重复提交，导致code重复使用而报错，微信头像等信息丢失
            //TODO 询问原理
            modelAndView.setViewName("redirect:/wx/toWxBind");
            session.setAttribute("wxUserInfo", wxUserInfo);

            return modelAndView;
        } else {
            //绑定过，就要去个人中心页面，去之前要查出个人信息，并传进session
            User user = userService.getUser(wxBind.getUserId());
            session.setAttribute("loginUser", user);
            modelAndView.setViewName("redirect:/user/toHome");

            return modelAndView;
        }
    }

    //关联userId
    @RequestMapping(value = "/wx/wxBind")
    @ResponseBody
    public ResponseDTO wxBind(String username, String password, HttpSession session) {

        User user = userService.login(username, password);

        WxUserInfo wxUserInfo = (WxUserInfo)session.getAttribute("wxUserInfo");

        //根据OpenId找到数据并更新userID
        userService.wxBind(user.getId(), wxUserInfo.getOpenid());
        //用于以后判断是否登录，如果session里没有这个user，说明没登录，那后续操作拦截器直接重定向到登录界面
        session.setAttribute("loginUser", user);

        return ResponseDTO.success();

    }

}
