package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 회사소개 페이지
    @GetMapping("/company")
    public String company() {
        return "company";
    }

    // 개인정보처리방침 페이지
    @GetMapping("/policy/privacy")
    public String privacyPolicy() {
        return "privacy";
    }
}
