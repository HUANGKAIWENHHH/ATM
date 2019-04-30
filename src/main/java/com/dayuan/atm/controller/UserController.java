package com.dayuan.atm.controller;

import com.baidu.aip.speech.AipSpeech;
import com.dayuan.atm.DTO.FlowDTO;
import com.dayuan.atm.DTO.ResponseDTO;
import com.dayuan.atm.captcha.Captcha;
import com.dayuan.atm.captcha.GifCaptcha;
import com.dayuan.atm.commons.AtmProperties;
import com.dayuan.atm.entity.Card;
import com.dayuan.atm.exception.BizException;
import com.dayuan.atm.holder.PageHolder;
import com.dayuan.atm.service.CardService;
import com.dayuan.atm.service.UserService;
//import com.dayuan.atm.util.CloudUtils;
import com.dayuan.atm.util.SpeechUtils;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

//符合标签，包含@ResponseBody（视图解析就会解析成json）
@Controller
public class UserController extends BaseController{

    //可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法
    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @Autowired
    private AtmProperties atmProperties;

    //注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO register(String username, String password, String confirmPassword, String code, HttpSession session) {
        try {
            if (StringUtils.isBlank(code)) {
                return ResponseDTO.faild("验证码错误");
            }

            Object obj = session.getAttribute("code");
            if (obj == null) {
                return ResponseDTO.faild("验证码过期");
            }
            if (!code.equalsIgnoreCase(String.valueOf(obj))) {
                return ResponseDTO.faild("验证码错误");
            }
            session.removeAttribute("code");

            userService.register(username, password, confirmPassword);
        } catch (BizException be) {
            return ResponseDTO.faild(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.faild("请联系客服");
        }
        return ResponseDTO.success();
    }

    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO login(String username, String password, HttpSession session) {
        session.setAttribute("loginUser", userService.login(username, password));

        return ResponseDTO.success();
    }

    //登出
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        //销毁session
        session.invalidate();
        return "redirect:/toLogin.do";
    }

   //上传
    /* @RequestParam 别名，若传入参数名称（MultipartFile file）里的file和网页上传文件表单里input框里name名字不一样（
     <input id="avatarFile" type="file" multiple="" name="avatar" onchange="uploadAvatar()">，name=avatar），
     controller里叫file，网页里叫avatar，可用别名方式映射*/
    @RequestMapping(value = "/user/uploadAvatar", method = RequestMethod.POST)
    public void uploadAvatar(String name, @RequestParam("avatar") MultipartFile file, HttpSession session, HttpServletResponse resp) {
      /* MultipartFile：实现文件上传
        需要导入springmvc上传文件multipartResolver bean  以及 commons-fileupload上传文件依赖jar包，
        才能解析上传文件型form表单里的普通表单*/
        //System.out.println("name=" + name);

        //file.getBytes() 获得文件字节数组
        //file.getInputStream() 获得文件输入流
        //file.getOriginalFilename() 获得文件原始名字
        //file.getName() 获得input表单里的name值
        //file.getContentType() 获得上传文件类型

        //将上传的文件命名为上传文件用户的ID并转存到。。。。（用绝对路径）
        try {
            file.transferTo(new File(atmProperties.getAvatarPath() + getUserId(session)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("重复触发");
        //return "redirect:/user/toHome.do";
        //如果使用@ResponseBody,那么该重定向方法就无效，只能返回字符串，但使用 response.sendRedirect("url");即可
        //try {
        //    resp.sendRedirect("/user/toHome.do");
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //parent:父，根源，调用父窗口的方法
        try {
            resp.getOutputStream().write("<script>parent.showAvatar()</script>".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将头像存放至项目文件夹内
    @RequestMapping(value = "/user/uploadAvatar2", method = RequestMethod.POST)
    public void uploadAvatar2(String name, @RequestParam("avatar") MultipartFile file, HttpSession session, HttpServletResponse resp) {

        try {
            //WebUtils.getRealPath 获取项目运行全路径
            //session.getServletContext() 返回一个ServletContext对象,该对象包含关于servlet运行环境的信息
            String path = WebUtils.getRealPath(session.getServletContext(), "/avatar");
            //transferTo  转存至。。。
            //new File()  文件创建类，该构造方法通过将给定路径名字字符串转换为抽象路径来创建一个新File实例
            file.transferTo(new File(path + "/" +getUserId(session)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            resp.getOutputStream().write("<script>parent.showAvatar()</script>".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //头像存放至云服务器
    //@RequestMapping(value = "/user/uploadAvatar3", method = RequestMethod.POST)
    //public void uploadAvatar3(HttpSession session,MultipartFile file) {
    //    try {
    //        String path = WebUtils.getRealPath(session.getServletContext(), "/avatar");
    //        file.transferTo(new File(path + "/" +getUserId(session)));
    //
    //
    //        File localFile = new File(path + "/" +getUserId(session));
    //
    //        // 指定要上传到 COS 上对象键
    //        String key = String.valueOf(getUserId(session));
    //
    //        PutObjectRequest putObjectRequest = new PutObjectRequest(CloudUtils.getBucketName(), key, localFile);
    //        PutObjectResult putObjectResult = CloudUtils.getCOSlient().putObject(putObjectRequest);
    //
    //
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    }
    //}

    //查看，返回头像
    @RequestMapping(value = "/user/loadMyAvatar", method = RequestMethod.GET)
    public void loadMyAvatar(HttpSession session, HttpServletResponse resp) {
        try(FileInputStream fis = new FileInputStream(WebUtils.getRealPath(session.getServletContext(), "/avatar") + "/" + getUserId(session));
            OutputStream fos = resp.getOutputStream()) {
            byte[] bytes = new byte[1024];
            int length = -1;
            while (-1 != (length = fis.read(bytes))) {
                fos.write(bytes, 0 , length);
                fos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载流水
    @RequestMapping(value = "/user/downFlow", method = RequestMethod.GET)
    public void downFlow(HttpSession session, String password, int cardId, HttpServletResponse resp) {
        int currentPage = 1;
        PageHolder<List<FlowDTO>> pageHolder = null;
        Card card = cardService.getCard(cardId);

        //设置响应头格式
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/octet-stream, charset=utf-8");
        resp.setHeader("Content-Disposition", "attachment;filename=" + card.getCardNumber() + ".csv");

        //输出流  缓冲字符流，可以一行输出，输出的时候要将字符转换成字节(转换流)，才能保存到硬盘里
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))
        ){
            bw.write("卡号,金额,备注,时间");
            bw.newLine();
            bw.flush();

            do{
                //获取流水
                pageHolder = cardService.listFlow(currentPage ++, cardId, password, getUserId(session));
                List<FlowDTO> flowDTO = pageHolder.getData();
                for (FlowDTO flows : flowDTO) {
                    bw.write(flows.getCardNumber() + "," + flows.getAmount() +","+ flows.getFlowDesc() + "," + flows.getFlowCreateTime());
                    bw.newLine();
                    bw.flush();
                }
            }while (currentPage <= pageHolder.getTotalPage());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //获取验证码
    @RequestMapping(value = "/getCode")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) {
        try {
            //创建验证码类,生成图片
            Captcha captcha = new GifCaptcha(200,60,4);
            //输出
            captcha.out(resp.getOutputStream());

            //将验证码值传进session
            req.getSession().setAttribute("code", captcha.text());

        } catch (Exception e) {
            throw new BizException("验证码错误");
        }
    }

    //语音合成
    @RequestMapping(value = "/speech")
    public void Speech(String word, HttpSession session, HttpServletResponse resp) {
        try {
            resp.getOutputStream().write(SpeechUtils.SpeechSynthesizer(word));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
