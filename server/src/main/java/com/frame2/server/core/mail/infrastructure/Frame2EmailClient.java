package com.frame2.server.core.mail.infrastructure;

import com.frame2.server.core.mail.application.EmailClient;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Frame2EmailClient implements EmailClient {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(String to, String title, String content) {
        var mimeMessage = javaMailSender.createMimeMessage();
        try {
            var mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(title);
            mimeMessageHelper.setText(content, false);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new DomainException(ExceptionType.SEND_MAIL_FAIL, e);
        }
    }
}
