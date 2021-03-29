package com.itstep.ckj13_mvc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Ckj13MvcApplicationTests {

    @Test
    void contextLoads() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String str = "1357";
        System.out.println(encoder.encode(str));
    }

}
