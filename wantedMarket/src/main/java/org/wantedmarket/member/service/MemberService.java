package org.wantedmarket.member.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wantedmarket.member.domain.dto.request.JoinRequest;
import org.wantedmarket.member.domain.dto.request.LoginRequest;
import org.wantedmarket.member.domain.entity.Member;
import org.wantedmarket.member.repository.MemberRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(JoinRequest request) {
        if(memberRepository.findByLoginId(request.getId()).isPresent()) {
            log.info("Member with loginId {} already exists", request.getId());
            throw new RuntimeException("Member with loginId " + request.getId() + " already exists");
        }

        Member member = Member.builder()
                .loginId(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();

        memberRepository.save(member);
    }

    public void login(LoginRequest request, HttpServletRequest httpServletRequest) {
        Member member = memberRepository.findByLoginId(request.getId())
                .orElseThrow(() -> new RuntimeException("Member with loginId " + request.getId() + " not found"));

        if(!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        httpServletRequest.getSession().invalidate();
        HttpSession session = httpServletRequest.getSession(true);  // Session이 없으면 생성

        session.setAttribute("memberId", member.getId());
        session.setMaxInactiveInterval(1800);
    }

    public void logout(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession(false);
        if(session != null) {
            session.invalidate();
        } else {
            throw new RuntimeException("Session not found");
        }
    }
}
