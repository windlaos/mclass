package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /**
     * 회사 소개 페이지
     */
    @GetMapping("/company")
    public String showCompanyPage() {
        return "company"; // templates/company.html
    }

    /**
     * 개인정보 처리방침 페이지
     */
    @GetMapping("/privacy")
    public String showPrivacyPolicyPage() {
        return "privacy"; // templates/privacy.html
    }
}
