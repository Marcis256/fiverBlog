package com.fiverrBlog.fiverrBlog.service.Impl;

import com.fiverrBlog.fiverrBlog.model.MailStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(MailStructure mailStructure) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromMail);
        simpleMailMessage.setSubject("Message from web-site, Name: " + mailStructure.getName() + ", Email: " + mailStructure.getEmail());
        simpleMailMessage.setText(mailStructure.getMessage());
        simpleMailMessage.setTo("marcis256@inbox.lv");

        mailSender.send(simpleMailMessage);

        System.out.println("Email sent to: " + " marcis256@inbox.lv");
    }
}
