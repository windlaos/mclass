package com.example.demo.service;

import com.example.demo.entity.User;                     // ★ 우리의 엔티티 User
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다: " + username));

        // ★ Spring Security의 User는 '풀패스'로 명시해 충돌 방지
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(u.getUsername());

        builder.password(u.getPassword());
        builder.roles(u.getRole()); // 예: "ADMIN" 또는 "USER"

        return builder.build();
    }
}
