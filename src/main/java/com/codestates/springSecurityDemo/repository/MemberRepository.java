package com.codestates.springSecurityDemo.repository;

import com.codestates.springSecurityDemo.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByUsername(String username);
}
