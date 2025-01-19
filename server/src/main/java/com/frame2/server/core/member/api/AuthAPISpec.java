package com.frame2.server.core.member.api;

import com.frame2.server.core.member.payload.request.EmailSecretValidateRequest;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.support.response.IdResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@Tag(name = "인증 API입니다.")
public interface AuthAPISpec {

    IdResponse signUp(SignupRequest signupRequest);

    void signIn(SignInRequest signInRequest, HttpSession httpSession);

    void validate(EmailSecretValidateRequest request);

}
