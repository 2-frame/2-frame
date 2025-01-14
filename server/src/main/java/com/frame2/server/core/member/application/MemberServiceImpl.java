package com.frame2.server.core.member.application;

import com.frame2.server.core.member.infrastructure.BasicAuthenticationRepository;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.member.payload.SignupInfo;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final BasicAuthenticationRepository basicAuthenticationRepository;

    @Override
    public SignupInfo signup(SignupRequest signupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email())) {
            throw new DomainException(ExceptionType.DUPLICATE_EMAIL);
        }

        var member = memberRepository.save(signupRequest.toEntity());
        basicAuthenticationRepository.save(signupRequest.toEntity(member));

        return new SignupInfo(member.getId());
    }

}