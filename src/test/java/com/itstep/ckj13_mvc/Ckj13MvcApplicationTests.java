package com.itstep.ckj13_mvc;

import com.itstep.ckj13_mvc.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Slf4j
@SpringBootTest
class Ckj13MvcApplicationTests {
    private MailService mailService;

    @Autowired
    public Ckj13MvcApplicationTests(MailService mailService) {
        this.mailService = mailService;
    }

    @Test
    void sendMailTest() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("New Message");
        mailMessage.setFrom("notepp@mail.com");
        mailMessage.setTo("receiver@mail.com");
        mailMessage.setText("Lorem Ipsum");
        mailService.sendMail(mailMessage);
        log.info("Mail sent");
    }

    @Test
    @Disabled
    void contextLoads() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String str = "Qwerty1!";
        System.out.println(encoder.encode(str));
    }

}
