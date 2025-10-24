package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/login")
    public String loginForm() {
        return "admin/login";
    }

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/index";
    }
}
