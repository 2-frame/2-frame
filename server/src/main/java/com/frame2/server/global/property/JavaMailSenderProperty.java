package com.frame2.server.global.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationPropertiesBinding
@ConfigurationProperties("spring.mail")
public record JavaMailSenderProperty(
        String host,
        String username,
        String password,
        int port
) {
}