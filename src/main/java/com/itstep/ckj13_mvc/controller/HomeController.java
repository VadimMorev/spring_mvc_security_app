package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.entity.ConfirmToken;
import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.exception.UsernameOrEmailExistsException;
import com.itstep.ckj13_mvc.repo.ConfirmTokenRepository;
import com.itstep.ckj13_mvc.service.MailService;
import com.itstep.ckj13_mvc.service.UserService;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class HomeController {
    UserService userService;
    ConfirmTokenRepository confirmTokenRepository;
    MailService mailService;

    @Autowired
    public HomeController(UserService userService, ConfirmTokenRepository confirmTokenRepository, MailService mailService) {
        this.userService = userService;
        this.confirmTokenRepository = confirmTokenRepository;
        this.mailService = mailService;
    }


//    @GetMapping
//    public String index(Model model, Principal principal) {
//        model.addAttribute("username", principal.getName());
//        return "index";
//    }

    @GetMapping("/admin-page")
    public String adminPage() {
        return "admin-page";
    }

    @GetMapping("/manager-page")
    public String managerPage() {
        return "manager-page";
    }

    @GetMapping("/login")
    public String signIn() {
        return "login";
    }

    @GetMapping("/register")
    public String viewRegister(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@ModelAttribute(name = "user") User user) {
        try {
            User signedUser = userService.registerUser(user);
            ConfirmToken token = new ConfirmToken(signedUser);
            confirmTokenRepository.save(token);
            String confirmLink = "http://localhost:8080/confirm?token=" + token.getValue();
            String text = "Please, confirm your email via this link: " + confirmLink;
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setSubject("Confirm your email");
            mail.setFrom("NoteApp@Mail.com");
            mail.setTo(user.getEmail());
            mail.setText(text);
            mailService.sendMail(mail);
            return "redirect:/login";
        } catch (UsernameOrEmailExistsException e) {
            return "redirect:/register?denied";
        }
    }

    @GetMapping("/confirm")
    public String confirmEmailWithToken(@RequestParam(name = "token") String token) {
        ConfirmToken confirmToken = confirmTokenRepository.findByValue(token);
        User user = confirmToken.getUser();
        user.setEnabled(true);
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/restore-password")
    public String restorePassword() {
        return "restore-password";
    }

    @GetMapping("/remind")
    public String sendNewPassword(@ModelAttribute(name = "email") String email) {
        User user = userService.findUserByEmail(email);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        List rules = Arrays.asList(new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1), new CharacterRule(EnglishCharacterData.Digit,
                        1), new CharacterRule(EnglishCharacterData.Special, 1));
        String newPassword = passwordGenerator.generatePassword(8, rules);
        String text = "Hello, this is your new password: " + newPassword;
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Restore your password");
        mail.setFrom("NoteApp@Mail.com");
        mail.setTo(user.getEmail());
        mail.setText(text);
        mailService.sendMail(mail);
        user.setPassword(encoder.encode(newPassword));
        userService.saveUser(user);
        return "redirect:/login?email";
    }
}




