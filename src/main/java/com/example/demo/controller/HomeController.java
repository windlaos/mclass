package com.yourpackage.controller; // 실제 패키지명에 맞게 수정하세요

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.yourpackage.service.ProductService; // 패키지 경로 맞게 수정

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";  // index.html
    }

    @GetMapping("/company")
    public String company() {
        return "company"; // templates/company.html
    }

    @GetMapping("/privacy")
    public String privacy() {
        return "privacy"; // templates/privacy.html
    }
}
