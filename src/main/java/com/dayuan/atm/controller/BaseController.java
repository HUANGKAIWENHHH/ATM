package com.dayuan.atm.controller;

import com.dayuan.atm.DTO.ResponseDTO;
import com.dayuan.atm.entity.User;
import com.dayuan.atm.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;

public abstract class BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    public int getUserId(HttpSession session) {
        Object object = session.getAttribute("loginUser");
        if (object == null) {
            throw new BizException("请先登录");
        }
        User user = (User) object;
        return user.getId();

        //return 1000;

    }

    //该方法只处理BizException异常
    //@ResponseBody解析json串
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResponseDTO processBizException(BizException be) {
        logger.error(be.getMenssage(), be);
        return ResponseDTO.faild(be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO processException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseDTO.faild("请联系客服");
    }

}
