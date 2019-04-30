package com.dayuan.atm.controller;

import com.dayuan.atm.DTO.ResponseDTO;
import com.dayuan.atm.exception.BizException;
import com.dayuan.atm.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

//符合标签，包含@ResponseBody（视图解析就会解析成json）
@RestController
public class CardController extends BaseController{

    //可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法
    @Autowired
    private CardService cardService;

    //开户    @RequestMapping：为控制器指定可以处理那些URL请求
    @RequestMapping(value = "/card/openAccount", method = RequestMethod.POST)
    public ResponseDTO openAccount(String password, String confirmPassword, HttpSession session) {
        cardService.openAccount(password, confirmPassword,getUserId(session));

        return ResponseDTO.success();
    }

    //存款
    @RequestMapping(value = "/card/deposit", method = RequestMethod.POST)
    public ResponseDTO deposit(int cardId, String password, String amount, HttpSession session) {
        cardService.deposit(cardId, password, amount, getUserId(session));

        return ResponseDTO.success();
    }

    //取款
    @RequestMapping(value = "/card/withdrawal", method = RequestMethod.POST)
    public ResponseDTO withdrawal(int cardId, String password, String amount, HttpSession session) {
        cardService.withdrawal(cardId, password, amount, getUserId(session));

        return ResponseDTO.success();
    }

    //转账
    @RequestMapping(value = "/card/transfer", method = RequestMethod.POST)
    public ResponseDTO transfer(int cardOutId, String password, String amount, String cardInNum, HttpSession session) {
        cardService.transfer(cardOutId, password, amount, cardInNum, getUserId(session));

        return ResponseDTO.success();
    }

    //分页查询流水
    @RequestMapping(value = "/card/listFlow", method = RequestMethod.POST)
    public ResponseDTO listFlow(int currentPage, int cardId, String password, HttpSession session) {
        return ResponseDTO.success(cardService.listFlow(currentPage, cardId, password, getUserId(session)));
    }

    //查询银行卡
    @RequestMapping(value = "/card/listMyCard", method = RequestMethod.POST)
    public ResponseDTO listMyCard(HttpSession session) {
        return ResponseDTO.success(cardService.listMyCard(getUserId(session)));
    }

    //近10笔流水
    @RequestMapping(value = "/card/listFlowTop10", method = RequestMethod.POST)
    public ResponseDTO listFlowTop10(HttpSession session) {
        return ResponseDTO.success(cardService.listFlowTop10(getUserId(session)));
    }

}
