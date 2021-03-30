package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.config.SecurityConfig;
import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.print.DocFlavor;

@Controller
public class HomeController {
    UserRepository userRepository;
    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index() {
        return "index";
    }

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
    public String viewRegister(User user){
        return "register";
    }
    @PostMapping("/register")
    public String signUp(@ModelAttribute(name = "user") User user){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = new User();
        if (userRepository.findByUsername(user.getUsername())==null){
            newUser.setUsername(user.getUsername());
            newUser.setRole("ROLE_USER");
            newUser.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(newUser);
            return "redirect:/login";
        }else{
            return "redirect:/register?denied";
        }

    }
}
