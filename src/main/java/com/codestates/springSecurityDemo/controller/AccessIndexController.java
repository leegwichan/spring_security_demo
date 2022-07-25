package com.codestates.springSecurityDemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccessIndexController {

    // 접근 권한은 SecurityConfig 참고
    @GetMapping("/")
    public @ResponseBody String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public @ResponseBody String manager() {
        return "manager";
    }




    // 어노테이션을 통해 접근 제한
    // @Secure : 1개의 권한을 주고 싶을 때
    // @PreAuthorize : 1개 이상의 권한을 주고 싶을 때 사용
    // @PostAuthorize : 메서드가 실행되고 응답하기 직전에 권한을 검사하는데 사용

    @Secured("ROLE_ADMIN") // ADMIN 만 접근 가능
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "info";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // ADMIN, MANAGER 만 접근 가능
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "data";
    }
}
