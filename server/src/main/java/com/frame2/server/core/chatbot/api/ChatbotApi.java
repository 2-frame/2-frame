package com.frame2.server.core.chatbot.api;

import com.frame2.server.core.chatbot.application.ChatbotService;
import com.frame2.server.core.chatbot.payload.request.ChatbotRequest;
import com.frame2.server.core.chatbot.payload.response.ChatbotResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
public class ChatbotApi implements ChatbotApiSpec {

    private final ChatbotService chatbotServiceImpl;

    @Override
    @PostMapping("/asks")
    public ChatbotResponse askChatbot(@RequestBody ChatbotRequest request) {
        String question = request.question();
        String answerText = chatbotServiceImpl.callChatbotApi(question);
        return new ChatbotResponse(answerText);
    }
}
