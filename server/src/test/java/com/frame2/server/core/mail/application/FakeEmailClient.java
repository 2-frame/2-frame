package com.frame2.server.core.mail.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class FakeEmailClient implements EmailClient {

    private static final Logger log = LoggerFactory.getLogger(FakeEmailClient.class);

    @Override
    public void send(String to, String title, String content) {
        log.info("send email to {}", to);
    }
}