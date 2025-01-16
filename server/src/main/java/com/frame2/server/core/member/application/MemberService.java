package com.frame2.server.core.member.application;

import com.frame2.server.core.member.payload.SignInInfo;
import com.frame2.server.core.member.payload.SignupInfo;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.member.payload.response.MyInformationResponse;
import com.frame2.server.core.support.request.User;

public interface MemberService {

    SignupInfo signup(SignupRequest signupRequest);

    SignInInfo signIn(SignInRequest signInRequest);

    MyInformationResponse getMe(User user);
}
