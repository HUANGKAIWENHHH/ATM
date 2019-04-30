package com.dayuan.atm.interceptor;

import com.alibaba.fastjson.JSON;
import com.dayuan.atm.DTO.ResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//HandlerInterceptorAdapter:拦截器
public class LoginInInterceptor extends HandlerInterceptorAdapter {

    @Override
    //该方法将在请求处理之前进行调用
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object object = session.getAttribute("loginUser");
        if (object != null) {
            return true;
        }

        //对于请求拦截后重定向，分2种，ajax和非ajax
        //x-requested-with 请求头 区分ajax请求还是普通请求
        String ajax = request.getHeader("X-Requested-With");
        if (StringUtils.isBlank(ajax)) {
            response.sendRedirect("/toLogin.do");
            return false;
        } else {
            //ajax无法解析json，所以用输出流
            //JSON.toJSONString序列化，是将对象转化为Json字符串
            //JSON.parseObject，是将Json字符串转化为相应的对象
            response.setContentType("utf-8");
            response.getOutputStream().write(JSON.toJSONString(ResponseDTO.toLogin()).getBytes());
            return false;
        }

    }

    //在当前请求进行处理之后，也就是Controller方法调用之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
