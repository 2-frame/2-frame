package com.frame2.server.core.member.api;

import com.frame2.server.core.member.application.MemberService;
import com.frame2.server.core.member.payload.response.MyInformationResponse;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.annotations.MemberOnly;
import com.frame2.server.core.support.request.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberAPI implements MemberAPISpec {

    private final MemberService memberService;

    @Override
    @MemberOnly
    @GetMapping("/me")
    public MyInformationResponse getMe(@Auth User user) {
        return memberService.getMe(user);
    }
}
