package com.codestates.springSecurityDemo.auth;

import com.codestates.springSecurityDemo.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//  "/login" 주소에 요청이 오면 대신 로그인을 진행
// Security Session ⇒ Authentication ⇒ UserDetails
@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
    private Member member;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            // User의 권한을 리턴한다.
            public String getAuthority() {
                return member.getRole();
            }
        });
        return collection;
    }

    @Override
    // 비밀번호 정보 리턴
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    // ID 정보 리턴
    public String getUsername() {
        return member.getUsername();
    }

    // 따로 규칙이 없는 경우, return true;
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    // 암호 사용 기간이 지났는지에 관해 확인
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    // 특정 사이트 규칙에 따라 return false로 설정. (ex. 1년 동안 로그인을 하지 않았을 경우)
    public boolean isEnabled(){
        return true;
    }
}
