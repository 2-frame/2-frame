package com.frame2.server.core.member.api;

import com.frame2.server.core.member.application.AuthService;
import com.frame2.server.core.member.application.MemberService;
import com.frame2.server.core.member.payload.request.EmailSecretValidateRequest;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.support.response.IdResponse;
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
    private final AuthService authService;

    @Override
    @PostMapping("/sign-up")
    public IdResponse signUp(@RequestBody SignupRequest signupRequest) {
        return new IdResponse(memberService.signup(signupRequest).memberId());
    }

    @Override
    @PostMapping("sign-in")
    public void signIn(@RequestBody SignInRequest signInRequest, HttpSession httpSession) {
        var signInInfo = memberService.signIn(signInRequest);

        httpSession.setAttribute("signInInfo", signInInfo);
    }

    @Override
    @PostMapping("/validate/secret")
    public void validate(@RequestBody EmailSecretValidateRequest request) {
        authService.validate(request);
    }

}
