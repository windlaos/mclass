package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // 관리자 로그인 페이지
    @GetMapping("/login")
    public String adminLogin() {
        return "admin/login";
    }

    // 관리자 대시보드(로그인 성공 후 이동)
    @GetMapping
    public String dashboard() {
        return "admin/dashboard"; // 없으면 admin/product-list로 바꿔도 됨
    }
}
