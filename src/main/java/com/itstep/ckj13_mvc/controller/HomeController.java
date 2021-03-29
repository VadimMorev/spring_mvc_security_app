package com.itstep.ckj13_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.print.DocFlavor;

@Controller
public class HomeController {
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
}
