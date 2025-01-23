package com.frame2.server.core.chatbot.api;

import com.frame2.server.core.chatbot.payload.request.ChatbotRequest;
import com.frame2.server.core.chatbot.payload.response.ChatbotResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "챗봇 Api")
public interface ChatbotApiSpec {
    ChatbotResponse askChatbot(ChatbotRequest request);
}
