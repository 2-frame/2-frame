package com.frame2.server.core.member.application;

import com.frame2.server.core.member.payload.SignupInfo;
import com.frame2.server.core.member.payload.request.SignupRequest;

public interface MemberService {

    SignupInfo signup(SignupRequest signupRequest);

}
