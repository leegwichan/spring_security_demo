package com.codestates.springSecurityDemo.controller;

import com.codestates.springSecurityDemo.model.Member;
import com.codestates.springSecurityDemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HtmlpageController {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입 페이지
    @GetMapping("/join")
    public String join() {
        return "joinForm";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }

    // 회원가입 요청
    @PostMapping("/join")
    public String join(Member member){
        member.setRole("ROLE_MANAGER");

        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 패스워드 암호화
        member.setPassword(encPassword);

        memberRepository.save(member);
        return "redirect:/login";
    }
}
