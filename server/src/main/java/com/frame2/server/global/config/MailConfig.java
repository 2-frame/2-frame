package com.frame2.server.global.config;

import com.frame2.server.global.property.JavaMailSenderProperty;
import java.util.Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_DEBUG = "mail.smtp.debug";
    private static final String MAIL_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
    private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

    private final JavaMailSenderProperty javaMailSenderProperty;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(javaMailSenderProperty.host());
        javaMailSender.setUsername(javaMailSenderProperty.username());
        javaMailSender.setPassword(javaMailSenderProperty.password());
        javaMailSender.setPort(javaMailSenderProperty.port());

        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put(MAIL_SMTP_AUTH, true);
        properties.put(MAIL_DEBUG, true);
        properties.put(MAIL_CONNECTION_TIMEOUT, 1000);
        properties.put(MAIL_SMTP_STARTTLS_ENABLE, true);
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }
}
