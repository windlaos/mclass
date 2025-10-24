package com.example.demo.controller;

// import com.example.demo.entity.Product; //  
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // ✅ 메인 페이지 - 상품 목록
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";  // ✅ 메인 화면 템플릿 (상품 리스트 출력)
    }

    // ✅ 상품 상세 보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/"; // ❌ 상품 없으면 홈으로
        }
        model.addAttribute("product", product);
        return "product/detail"; // ✅ 상세 페이지 템플릿
    }

    // ✅ 장바구니 임시 처리 (console log)
    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Long id) {
        System.out.println("장바구니 추가: ID = " + id);
        return "redirect:/";
    }
}
