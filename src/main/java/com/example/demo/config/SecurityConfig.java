package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService customUserDetailsService;

    /** BCrypt 인코더 설정 */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 관리자(admin) 영역 보안 설정 */
    @Bean
    public SecurityFilterChain adminChain(HttpSecurity http) throws Exception {

        http
            .securityMatcher("/admin/**")
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/admin/login",
                        "/admin/css/**",
                        "/admin/js/**"
                ).permitAll()
                .anyRequest().hasRole("ADMIN")  // ✅ DB에는 ADMIN으로 저장, Spring이 ROLE_ 자동 붙임
            )
            .formLogin(login -> login
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .defaultSuccessUrl("/admin", true)
                .failureUrl("/admin/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }

    /** 일반 사용자(public) 영역 보안 설정 */
    @Bean
    public SecurityFilterChain appChain(HttpSecurity http) throws Exception {

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/", "/company", "/privacy",
                        "/css/**", "/js/**", "/images/**", "/static/**",
                        "/products/**", "/product/**",
                        "/cart/**", "/api/cart/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable());

        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();
    }


    /** 인증 공급자 설정 (UserDetailsService + PasswordEncoder) */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}