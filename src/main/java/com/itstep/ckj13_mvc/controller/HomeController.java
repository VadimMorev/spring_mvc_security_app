package com.itstep.ckj13_mvc.controller;

import com.itstep.ckj13_mvc.entity.User;
import com.itstep.ckj13_mvc.exception.UsernameOrEmailExistsException;
import com.itstep.ckj13_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
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
    public String viewRegister(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@ModelAttribute(name = "user") User user,
                         HttpServletRequest request) {
        try {
            userService.registerUser(user);
            request.login(user.getUsername(), user.getPassword());
            return "redirect:/";
        } catch (UsernameOrEmailExistsException e) {
            return "redirect:/register?denied";
        } catch (ServletException e) {
            e.printStackTrace();
            return "redirect:/login";
        }
    }
}




