package com.frame2.server.core.member.api;

import com.frame2.server.core.member.application.MemberService;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthAPI implements AuthAPISpec {

    private final MemberService memberService;

    @Override
    @PostMapping("/sign-up")
    public void signUp(@RequestBody SignupRequest signupRequest) {
        memberService.signup(signupRequest);
    }

    @Override
    @PostMapping("sign-in")
    public void signIn(@RequestBody SignInRequest signInRequest, HttpSession httpSession) {
        var signInInfo = memberService.signIn(signInRequest);

        httpSession.setAttribute("signInInfo", signInInfo);
    }

}
