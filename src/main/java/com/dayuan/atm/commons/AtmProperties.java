package com.dayuan.atm.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//组件
@Component
public class AtmProperties {

    //加载自定义配置文件
    @Value("${atm.path}")
    private String avatarPath;

    public String getAvatarPath() {
        return avatarPath;
    }

}
