//package com.dayuan.atm.holder;
//
//import com.alibaba.fastjson.JSON;
//import com.dayuan.atm.util.HttpUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class WxBindHolder {
//
//    public Logger logger = LoggerFactory.getLogger(WxBindHolder.class);
//
//    @Value("${wx.appid}")
//    private String appid;
//
//    @Value("${wx.qrconnect.url}")
//    private String qrconnectUrl;
//
//    @Value("${wx.redirect.uri}")
//    private String redirectUri;
//
//    @Value("${wx.accessToken.url}")
//    private String accessToken;
//
//    @Value("${wx.secret}")
//    private String secret;
//
//    @Value("${wx.userInfo.url}")
//    private String userInfoUrl;
//
//    //生成二维码地址
//    public String createQrconnectUrl() {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(qrconnectUrl)
//                .append("?")
//                .append("appid=").append(appid).append("&")
//                .append("redirect_uri=").append(redirectUri).append("&")
//                .append("response_type=code&scope=snsapi_login&state=STATE");
//
//        return stringBuilder.toString();
//    }
//
//    //获取accessToken
//    public AccessTokenInfo getAccessToken(String code) {
//        //获取acessToken
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(accessToken)
//                .append("?")
//                .append("appid=").append("appid").append("&")
//                .append("secret=").append("secret").append("&")
//                .append("code=").append(code).append("&")
//                .append("grant_type=authorization_code");
//        //获取json串
//        String result = HttpUtils.get(stringBuilder.toString());
//
//        //JSON抓换成AccessToken对象
//        return JSON.parseObject(result, AccessTokenInfo.class);
//
//    }
//
//    //获取用户信息
//    public WxUserInfo getWxUserInfo(String accessToken, String openId) {
//        //获取用户信息
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(userInfoUrl)
//                .append("?")
//                .append("access_token=").append(accessToken).append("&")
//                .append("openid=").append(openId);
//        //获取json串
//        String result = HttpUtils.get(stringBuilder.toString());
//
//        //JSON抓换成AccessToken对象
//        return JSON.parseObject(result, WxUserInfo.class);
//    }
//}
