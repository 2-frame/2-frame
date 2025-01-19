package com.frame2.server.core.member.infrastructure;

import com.frame2.server.core.member.domain.RandomGenerator;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCodeGenerator implements RandomGenerator {

    private static final int SECRET_LENGTH = 6;

    @Override
    public String generate() {
        return UUID.randomUUID()
                .toString()
                .substring(0, SECRET_LENGTH);
    }
}
