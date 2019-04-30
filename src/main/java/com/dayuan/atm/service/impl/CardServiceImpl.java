package com.dayuan.atm.service.impl;


import com.dayuan.atm.DTO.CardDTO;
import com.dayuan.atm.DTO.FlowDTO;
import com.dayuan.atm.entity.Card;
import com.dayuan.atm.entity.Flow;
import com.dayuan.atm.exception.BizException;
import com.dayuan.atm.holder.PageHolder;
import com.dayuan.atm.mapper.CardMapper;
import com.dayuan.atm.mapper.FlowMapper;
import com.dayuan.atm.service.CardService;
import com.dayuan.atm.service.RedicService;
import com.dayuan.atm.util.CardUtils;
import com.dayuan.atm.util.DateUtils;
import com.dayuan.atm.util.MoneyUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    //@Autowired它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过@Autowired的使用来消除 set ，get方法。
    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private FlowMapper flowMapper;

    @Autowired
    private RedicService redicService;

    @Override
    public void openAccount(String password, String confirmPassword, int userId) {
        //StringUtils.isBlank() commons-lang包下边，用于判断是否为空以及是否全部是空格
        if (StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            throw new BizException("请输入密码");
        }

        if (!password.equals(confirmPassword)) {
            throw new BizException("两次密码输入不同");
        }

        String cardNumber = CardUtils.createCardNum();
        System.out.println(cardNumber);
        Card card = cardMapper.getCardbyNumber(cardNumber);
        if (card != null) {
            throw new BizException("卡号重复，请重试");
        }
        card = new Card();
        card.setCardBalance(0);
        card.setCardCreateTime(new Date());
        card.setCardModifyTime(new Date());
        card.setCardNumber(cardNumber);
        //通过消息摘要算法 将密码加密后再保存到数据库中 另外还需要防止彩虹表 所以需要加盐
        card.setCardPassword(DigestUtils.md5Hex(password + userId));
        card.setCardStatus(1);
        card.setUserId(userId);
        int rows = cardMapper.insert(card);
        if (rows != 1) {
            throw new BizException("开户失败");
        }

        //更改了卡的状态，所以要让缓存失效，重新读取
        redicService.expire(redicService.getKey("card", "myCard", String.valueOf(userId)), 1);

    }

    //@Transactional    事务标签，该方法就具备事务特性，方法走完后spring会自动提交，若异常，会自动回滚
    @Override
    @Transactional
    public void deposit(int cardId, String password, String amount, int userId) {
        //返回对当前正在执行的线程对象的引用（获取当前线程）
        //System.out.println("thread:" + Thread.currentThread().getName());

        //Card card = cardMapper.selectByPrimaryKey(cardId);
        //悲观锁
        Card card = cardMapper.getCardById4Lock(cardId);
        if (card == null) {
            throw new BizException("卡号或密码错误");
        }

        if (!DigestUtils.md5Hex(password + userId).equals(card.getCardPassword())) {
            throw new BizException("卡号或密码错误");
        }

        if (StringUtils.isBlank(amount)) {
            throw new BizException("请输入存款金额");
        }

        //乐观锁
        int newbalance = card.getCardBalance() + MoneyUtils.mul(amount);
        int rows = cardMapper.updateBalance(cardId, newbalance, card.getCardBalance());
        if (rows != 1) {
            throw new BizException("存款失败");
        }

        // 记录流水
        Flow flow = new Flow();
        flow.setCardId(cardId);
        flow.setUserId(userId);
        flow.setFlowCreateTime(new Date());
        flow.setFlowType(1);
        flow.setFlowDesc("存款");
        flow.setAmount(MoneyUtils.mul(amount));
        flow.setCardNumber(card.getCardNumber());
        rows = flowMapper.insert(flow);
        if (rows != 1) {
            throw new BizException("存款失败");
        }

        //更改了卡的状态，所以要让缓存失效，重新读取
        redicService.expire(redicService.getKey("card", "myCard", String.valueOf(userId)), 1);

    }

    @Override
    @Transactional
    public void withdrawal(int cardId, String password, String amount, int userId) {
        if (StringUtils.isBlank(password) || StringUtils.isBlank(amount)) {
            throw new BizException("密码或取款金额不能为空");
        }

        if (MoneyUtils.mul(amount) <= 0) {
            throw new BizException("取款金额必须大于零");
        }

        Card card = cardMapper.selectByPrimaryKey(cardId);
        if (card == null) {
            throw new BizException("卡号或密码错误");
        }

        if (!DigestUtils.md5Hex(password + userId).equals(card.getCardPassword())) {
            throw new BizException("卡号或密码错误");
        }

        int newbalance = card.getCardBalance() - MoneyUtils.mul(amount);
        if (newbalance < 0) {
            throw new BizException("余额不足");
        }

        int rows = cardMapper.updateBalance(cardId, newbalance, card.getCardBalance());
        if (rows != 1) {
            throw new BizException("取款失败");
        }

        //记录流水
        Flow flow = new Flow();
        flow.setCardId(cardId);
        flow.setUserId(userId);
        flow.setFlowCreateTime(new Date());
        flow.setFlowType(2);
        flow.setFlowDesc("取款");
        flow.setAmount(MoneyUtils.mul(amount));
        flow.setCardNumber(card.getCardNumber());
        rows = flowMapper.insert(flow);
        if (rows != 1) {
            throw new BizException("取款失败");
        }

        //更改了卡的状态，所以要让缓存失效，重新读取
        redicService.expire(redicService.getKey("card", "myCard", String.valueOf(userId)), 1);

    }

    @Override
    @Transactional
    public void transfer(int cardOutId, String password, String amount, String cardInNum, int userId) {
        Card cardOut = cardMapper.selectByPrimaryKey(cardOutId);
        if (cardOut == null) {
            throw new BizException("卡号或密码错误");
        }

        if (!DigestUtils.md5Hex(password + userId).equals(cardOut.getCardPassword())) {
            throw new BizException("卡号或密码错误");
        }

        if (MoneyUtils.mul(amount) <= 0) {
            throw new BizException("转账金额必须大于零");
        }

        //检验余额
        int newbalance = cardOut.getCardBalance() - MoneyUtils.mul(amount);
        if (newbalance < 0) {
            throw new BizException("银行卡余额不足");
        }

        // 转入的银行卡是否存在 是否注销 是否可用
        Card cardIn = cardMapper.getCardbyNumber(cardInNum);
        if (cardIn == null || cardIn.getCardStatus() != 1) {
            throw new BizException("目标卡号不存在或状态异常");
        }

        //转账支出
        int rows = cardMapper.updateBalance(cardOutId, newbalance, cardOut.getCardBalance());
        if (rows != 1) {
            throw new BizException("转账失败");
        }

        //转出流水
        Flow flowOut = new Flow();
        flowOut.setAmount(MoneyUtils.mul(amount));
        flowOut.setCardNumber(cardOut.getCardNumber());
        flowOut.setFlowDesc("转账支出");
        flowOut.setFlowCreateTime(new Date());
        flowOut.setUserId(cardOut.getUserId());
        flowOut.setCardId(cardOut.getId());
        flowOut.setFlowType(3);
        rows = flowMapper.insert(flowOut);
        if (rows != 1) {
            throw new BizException("转账失败");
        }

        //转账收入
        newbalance = cardIn.getCardBalance() + MoneyUtils.mul(amount);
        rows = cardMapper.updateBalance(cardIn.getId(), newbalance, cardIn.getCardBalance());
        if (rows != 1) {
            throw new BizException("转账失败");
        }

        //转入流水
        Flow flowIn = new Flow();
        flowIn.setFlowType(4);
        flowIn.setCardId(cardIn.getId());
        flowIn.setUserId(cardIn.getUserId());
        flowIn.setFlowCreateTime(new Date());
        flowIn.setCardNumber(cardIn.getCardNumber());
        flowIn.setAmount(MoneyUtils.mul(amount));
        flowIn.setFlowDesc("转账收入");
        rows = flowMapper.insert(flowIn);
        if (rows != 1) {
            throw new BizException("转账失败");
        }

        //更改了卡的状态，所以要让缓存失效，重新读取
        redicService.expire(redicService.getKey("card", "myCard", String.valueOf(userId)), 1);

    }

    @Override
    public PageHolder<List<FlowDTO>> listFlow(int currentPage, int cardId, String password, int userId) {
        Card card = cardMapper.selectByPrimaryKey(cardId);
        if (card == null) {
            throw new BizException("卡号或密码错误");
        }
        if (!DigestUtils.md5Hex(password + userId).equals(card.getCardPassword())) {
            throw new BizException("卡号或密码错误");
        }

        PageHolder<List<FlowDTO>> pageHolder = PageHolder.build(currentPage, flowMapper.countFlow(cardId));
        List<Flow> flows = flowMapper.listFlow(cardId, pageHolder.offset(), PageHolder.PAGENUM);

        List<FlowDTO> flowDTO = new ArrayList<>(flows.size());
        //for(元素类型t    元素变量x :    遍历对象obj){
        //    引用了x的java语句;
        //}
        for (Flow flow : flows) {
            FlowDTO flowDTO1 = new FlowDTO();
            flowDTO1.setAmount(MoneyUtils.div(String.valueOf(flow.getAmount())));
            flowDTO1.setCardNumber(flow.getCardNumber());
            flowDTO1.setFlowDesc(flow.getFlowDesc());
            flowDTO1.setFlowCreateTime(DateUtils.dateToSting(flow.getFlowCreateTime()));

            flowDTO.add(flowDTO1);
        }
        pageHolder.setData(flowDTO);
        return pageHolder;
    }

    @Override
    public List<CardDTO> listMyCard(int userid) {
        //先去缓存里找，缓存里没有，在从数据库找
        List<CardDTO> cardDTOS = redicService.getCard(userid);
        //判断集合是否为空，集合不要返回null
        if (!cardDTOS.isEmpty()) {
            return cardDTOS;
        }

        List<Card> cards = cardMapper.listCardByUserId(userid);

        List<CardDTO> cardDTO = new ArrayList<>(cards.size());
        for (Card card : cards) {
            CardDTO cardDTO1 = new CardDTO();
            cardDTO1.setAmount(MoneyUtils.div(String.valueOf(card.getCardBalance())));
            cardDTO1.setUserId(userid);
            cardDTO1.setId(card.getId());
            cardDTO1.setCardNumber(card.getCardNumber().substring(0, 13) + "*****" + card.getCardNumber().substring(18, 19));

            cardDTO.add(cardDTO1);
        }

        //放入缓存
        redicService.cacheCard(cardDTO, userid);

        return cardDTO;
    }

    @Override
    public List<FlowDTO> listFlowTop10(int userId) {
        List<Flow> flows = flowMapper.listFlowTop10(userId);
        //for (Flow flow : flows) {
        //    flow.setCardNumber();
        //}

        List<FlowDTO> flowDTO = new ArrayList<>(flows.size());
        for (Flow flow : flows) {
            FlowDTO flowDTO1 = new FlowDTO();
            flowDTO1.setAmount(MoneyUtils.div(String.valueOf(flow.getAmount())));
            flowDTO1.setCardNumber(flow.getCardNumber());
            flowDTO1.setFlowDesc(flow.getFlowDesc());
            flowDTO1.setFlowCreateTime(DateUtils.dateToSting(flow.getFlowCreateTime()));

            flowDTO.add(flowDTO1);
        }

        return flowDTO;
    }

    @Override
    public Card getCard(int cardId) {
        return cardMapper.selectByPrimaryKey(cardId);
    }
}

