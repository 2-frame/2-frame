package com.frame2.server.core.member.application;

import com.frame2.server.core.member.infrastructure.MemberRepository;
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

    @Override
    public void signup(SignupRequest signupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email())) {
            throw new DomainException(ExceptionType.DUPLICATE_MEMBER_ERROR);
        }
        memberRepository.save(signupRequest.toEntity());
    }

}