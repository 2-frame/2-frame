package com.frame2.server.core.chatbot.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frame2.server.core.chatbot.payload.request.ChatbotRequest;
import com.frame2.server.core.chatbot.payload.response.ChatbotResponse;
import com.frame2.server.core.support.exception.DomainException;
import com.frame2.server.core.support.exception.ExceptionType;
import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ChatbotServiceImpl implements ChatbotService {

    private static final Logger log = LoggerFactory.getLogger(ChatbotServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    public record T(String question){}
    public String callChatbotApi(String userQuestion) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("question", userQuestion);

        ChatbotResponse response = null;
        try {
            response = restClient
                    .post()
                    .uri("http://127.0.0.1:8000/chat")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(objectMapper.writeValueAsBytes(new T("안녕")))
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            (request, clientResponse) -> {
                                log.info("{}", clientResponse.getStatusCode());
                                log.info("{}", clientResponse.getBody());

                                StringBuilder result = new StringBuilder();
                                try (BufferedInputStream bufferedInputStream = new BufferedInputStream(clientResponse.getBody())) {
                                    int byteData;
                                    while ((byteData = bufferedInputStream.read()) != -1) {
                                        result.append((char) byteData);
                                    }
                                }
                                log.info("{}", result);
                                throw new DomainException(ExceptionType.CHATBOT_CALL_FAIL);
                            }
                    )
                    .body(ChatbotResponse.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (response == null) {
            return "(no answer)";
        }

        return response.answer();
    }
}
