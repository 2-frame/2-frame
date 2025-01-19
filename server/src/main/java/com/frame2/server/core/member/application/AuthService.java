package com.frame2.server.core.member.application;

import com.frame2.server.core.member.payload.request.EmailSecretValidateRequest;

public interface AuthService {

    void validate(EmailSecretValidateRequest request);
}
