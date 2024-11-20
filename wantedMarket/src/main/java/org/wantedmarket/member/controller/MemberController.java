package org.wantedmarket.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wantedmarket.member.domain.dto.request.JoinRequest;
import org.wantedmarket.member.domain.dto.request.LoginRequest;
import org.wantedmarket.member.service.MemberService;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(path = "/join")
    public void join(@RequestBody JoinRequest joinRequest) {
        memberService.join(joinRequest);
    }

    @PostMapping(path = "/login")
    public void login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        memberService.login(request, httpServletRequest);
    }

    @PostMapping(path = "/logout")
    public void logout(HttpServletRequest httpServletRequest) {
        memberService.logout(httpServletRequest);
    }
}
