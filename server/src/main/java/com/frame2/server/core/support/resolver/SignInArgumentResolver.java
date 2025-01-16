package com.frame2.server.core.support.resolver;

import com.frame2.server.core.member.payload.SignInInfo;
import com.frame2.server.core.support.annotations.Auth;
import com.frame2.server.core.support.request.User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class SignInArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Auth.class)
                && Objects.equals(parameter.getParameter().getType(), User.class);
    }

    @Override
    public User resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {

        HttpServletRequest httpServletRequest = (HttpServletRequest) webRequest.getNativeRequest();

        return Optional.ofNullable(httpServletRequest.getSession())
                .map(session -> session.getAttribute("signInInfo"))
                .map(SignInInfo.class::cast)
                .map(SignInInfo::memberId)
                .map(User::member)
                .orElseGet(User::guest);
    }
}