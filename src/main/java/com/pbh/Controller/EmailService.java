package com.pbh.Controller;

import org.aspectj.apache.bcel.classfile.Code;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class EmailService {
    
    @Autowired
    private JavaMailSender send;

    @Value("${spring.mail.username}")
    private String fromName;
    @GetMapping("/email")
    @ResponseBody
    public String sendSimpleMail(HttpSession session,String username){
        String code=VerifyUtils.verifyCode();
        session.setAttribute("code",code);
        System.out.println("============================开始邮件发送============================");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromName); //设置发送人邮箱
        simpleMailMessage.setTo(username);  //设置接收人邮箱
        simpleMailMessage.setSubject("Test"); //邮箱主题
        simpleMailMessage.setText(code); //邮箱内容
        try {
            send.send(simpleMailMessage);
            System.out.println("============================邮件发送成功============================");
            return code;
        }catch (Exception e){

            System.out.println("【邮件发送异常】=={}"+e);
            System.out.println("============================邮件发送失败============================");
            e.printStackTrace();
            return "error";
        }
    }
}
