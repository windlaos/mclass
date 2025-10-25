package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/company")
    public String company() {
        return "company"; // templates/company.html
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy"; // templates/privacy.html
    }
}

