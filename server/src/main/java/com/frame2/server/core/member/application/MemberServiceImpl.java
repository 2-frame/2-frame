package com.frame2.server.core.member.application;

import com.frame2.server.core.member.domain.Member;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.member.payload.request.SignupRequest;
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
        memberRepository.findByEmail(signupRequest.email())
                .ifPresent((_) -> {
                    throw new IllegalArgumentException("중복 회원가입");
                });

        memberRepository.save(Member.builder()
                .email(signupRequest.email())
                .name(signupRequest.name())
                .nickname(signupRequest.nickname())
                .build());
    }
}