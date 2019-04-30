package com.dayuan.atm.beanconfig;

import com.dayuan.authen.wechat.WxBindHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，
这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，
并用于构建bean定义，初始化Spring容器
*/
public class WeChatBeanConfig {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.qrconnect.url}")
    private String qrconnectUrl;

    @Value("${wx.redirect.uri}")
    private String redirectUri;

    @Value("${wx.accessToken.url}")
    private String accessToken;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.userInfo.url}")
    private String userInfoUrl;

    @Bean
    /*
    用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。
    产生这个Bean对象的方法Spring只会调用一次，随后这个Spring将会将这个Bean对象放在自己的IOC容器中
    */
    public WxBindHolder wxBindHolder() {
        WxBindHolder wxBindHolder = new WxBindHolder();
        wxBindHolder.setAccessToken(accessToken);
        wxBindHolder.setAppid(appid);
        wxBindHolder.setQrconnectUrl(qrconnectUrl);
        wxBindHolder.setRedirectUri(redirectUri);
        wxBindHolder.setSecret(secret);
        wxBindHolder.setUserInfoUrl(userInfoUrl);

        return wxBindHolder;
    }

}
