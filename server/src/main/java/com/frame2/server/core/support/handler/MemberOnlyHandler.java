package com.frame2.server.core.support.handler;

import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import com.frame2.server.core.support.request.User;
import java.util.Arrays;
import java.util.Objects;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MemberOnlyHandler {
    @Before("@annotation(com.frame2.server.core.support.annotations.MemberOnly)")
    public void check(JoinPoint joinPoint) {
        Arrays.stream(joinPoint.getArgs())
                .filter(Objects::nonNull)
                .filter(arg -> arg.getClass().equals(User.class))
                .map(User.class::cast)
                .filter(User::isMember)
                .findFirst()
                .orElseThrow(() -> new DomainException(ExceptionType.UNAUTHORIZED_ERROR));
    }
}
