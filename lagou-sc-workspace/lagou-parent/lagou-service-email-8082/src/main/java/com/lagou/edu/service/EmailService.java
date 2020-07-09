package com.lagou.edu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    /**
     * 发送验证码到邮箱，true成功，false失败
     * @param email
     * @param subject
     * @param code
     * @return
     */
    public String sendSimpleMail(String email, String subject, String code) {
        SimpleMailMessage message = new SimpleMailMessage();

        String text = String.format("这是你的验证码%s，十分钟内有效。%n", code);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return "-1";
        }
        return "1";
    }

}
