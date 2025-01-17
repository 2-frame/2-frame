package com.frame2.server.core.mail.application;

public interface EmailClient {

    void send(String to, String title, String content);
}
