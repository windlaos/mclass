package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // ✅ 정적 리소스 허용
                        .requestMatchers(
                                "/", "/company", "/privacy",
                                "/css/**", "/js/**", "/images/**", "/static/**"
                        ).permitAll()

                        // ✅ 상품 조회 및 장바구니 접근 허용
                        .requestMatchers("/product/**", "/cart/**").permitAll()

                        // ✅ 관리자 페이지는 로그인 필요
                        .requestMatchers("/admin/**").authenticated()

                        // ✅ 나머지는 모두 허용
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login
                        // ✅ 관리자 로그인 페이지 설정
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        // ✅ 관리자 로그아웃 설정
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/")
                );

        return http.build();
    }
}

