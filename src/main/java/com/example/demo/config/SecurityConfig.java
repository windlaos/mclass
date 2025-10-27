package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /** 관리자 영역 전용 체인: /admin/** */
    @Bean
    public SecurityFilterChain adminChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        http.securityMatcher(mvc.pattern("/admin/**"))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(mvc.pattern("/admin/login"), mvc.pattern("/admin/css/**"), mvc.pattern("/admin/js/**")).permitAll()
                .anyRequest().hasRole("ADMIN")
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
            )
            .csrf(csrf -> csrf.disable());

        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    /** 고객(공용) 체인: 나머지 경로 */
    @Bean
    public SecurityFilterChain appChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

        http.authorizeHttpRequests(auth -> auth
                // 정적/공용
                .requestMatchers(
                    mvc.pattern("/"),
                    mvc.pattern("/company"),
                    mvc.pattern("/privacy"),
                    mvc.pattern("/css/**"),
                    mvc.pattern("/js/**"),
                    mvc.pattern("/images/**"),
                    mvc.pattern("/static/**")
                ).permitAll()
                // 쇼핑/장바구니(로그인 없이 가능)
                .requestMatchers(
                    mvc.pattern("/products"),
                    mvc.pattern("/product/**"),
                    mvc.pattern("/cart/**"),
                    mvc.pattern("/api/cart/**")
                ).permitAll()
                // 그 외는 로그인 필요(고객용 로그인 페이지는 선택사항)
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/login")                   // 필요 시 사용 (없으면 404라도 보안엔 지장 없음)
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
            )
            .csrf(csrf -> csrf.disable());

        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(customUserDetailsService);
        p.setPasswordEncoder(passwordEncoder());
        return p;
    }
}
