package org.chatwebsocket.service;

import org.chatwebsocket.dto.ChatMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;

    public ChatService(KafkaTemplate<String, ChatMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ChatMessage message) {
        try {
            kafkaTemplate.send("chat-topic", message.getReceiver(), message).get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<String> getChatHistory() {
        return List.of();
    }
}
