package com.frame2.server.core.member.application;

import com.frame2.server.core.member.domain.LoginStatus;
import com.frame2.server.core.member.domain.MemberCredential;
import com.frame2.server.core.member.infrastructure.MemberCredentialRepository;
import com.frame2.server.core.member.infrastructure.MemberRepository;
import com.frame2.server.core.member.payload.SignInInfo;
import com.frame2.server.core.member.payload.SignupInfo;
import com.frame2.server.core.member.payload.request.SignInRequest;
import com.frame2.server.core.member.payload.request.SignupRequest;
import com.frame2.server.core.member.payload.response.MyInformationResponse;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import com.frame2.server.core.support.request.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberCredentialRepository memberCredentialRepository;
    private final MemberEmailService memberEmailService;

    @Override
    public SignupInfo signup(SignupRequest signupRequest) {
        if (memberRepository.existsByEmail(signupRequest.email())) {
            throw new DomainException(ExceptionType.DUPLICATE_EMAIL);
        }

        if (memberRepository.existsByNickname(signupRequest.nickname())) {
            throw new DomainException(ExceptionType.DUPLICATE_NICKNAME);
        }

        var member = memberRepository.save(signupRequest.toEntity());
        memberCredentialRepository.save(signupRequest.toEntity(member));

        memberEmailService.sendSecret(member);

        return new SignupInfo(member.getId());
    }

    @Override
    public SignInInfo signIn(SignInRequest signInRequest) {
        return memberCredentialRepository.findByEmail(signInRequest.email())
                .filter(authentication -> authentication.tryLogin(signInRequest.password()) == LoginStatus.OK)
                .map(MemberCredential::getMemberId)
                .map(SignInInfo::new)
                .orElseThrow(() -> new DomainException(ExceptionType.LOGIN_FAIL));
    }

    @Override
    @Transactional(readOnly = true)
    public MyInformationResponse getMe(User user) {
        return MyInformationResponse.from(memberRepository.findOne(user.id()));
    }

}