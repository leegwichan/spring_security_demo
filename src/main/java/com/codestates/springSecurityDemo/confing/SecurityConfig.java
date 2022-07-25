package com.codestates.springSecurityDemo.confing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// securedEnabled : Secured 애너테이션이 활성화, Controller에서 애너테이션으로 관리 가능
// prePostEnabled : PreAuthorize, PostAuthorize 애너테이션이 활성화
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable(); // form 태그로만 요청 가능, postman 등의 요청이 불가능
        http.headers().frameOptions().disable(); // h2 연결시 필요

        http.authorizeRequests()
                // 로그인 한 사람만
                .antMatchers("/user/**").authenticated()
                // ADMIN, MANAGER 만 ("ROLE_" 생략 가능)
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                // ADMIN 만 ("ROLE_" 생략 가능)
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                // 이외 주소는 모두 접속 허용
                .anyRequest().permitAll()
                // 권한 없는 페이지로 접속했을 때 login 페이지로 이동
                .and().formLogin().loginPage("/login");

        return http.build();
    }

    // 패스워드 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
