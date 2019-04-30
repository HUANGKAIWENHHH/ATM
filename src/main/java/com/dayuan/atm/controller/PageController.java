package com.dayuan.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class PageController extends BaseController{

    @RequestMapping(value = "/user/toHome")
    public String toHome() {
        return "home";
    }

    @RequestMapping(value = "/user/toOpenAccount")
    public String toOpenAccount() {
        return "openaccount";
    }

    @RequestMapping(value = "/user/toDeposit")
    public String deposit() {
        return "deposit";
    }

    @RequestMapping(value = "/user/toWithdrawal")
    public String withdrawal() {
        return "draw";
    }

    @RequestMapping(value = "/user/toTransfer")
    public String transfer() {
        return "transfer";
    }

    @RequestMapping(value = "/user/toFlow")
    public String listFlow() {
        return "flow";
    }

    //注册
    @RequestMapping(value = "/toRegister")
    public ModelAndView toRegister() {
        //Springmvc的野心，干掉servlet
        ModelAndView modelAndView = new ModelAndView();
        //添加视图名字
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }

    //登录
    @RequestMapping(value = "/toLogin")
    public ModelAndView toLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    //欢迎页面
    @RequestMapping(value = "/")
    public String welcome(HttpSession session, HttpServletResponse resp) {
        try {
            getUserId(session);
        } catch (Exception e) {
            return "login";
        }
        return "Redirect:/login";
    }

    @RequestMapping(value = "/wx/toWxBind")
    public ModelAndView toWxBind() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wxBind");
        return modelAndView;
    }
}
