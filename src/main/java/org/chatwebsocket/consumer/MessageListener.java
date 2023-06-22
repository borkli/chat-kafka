package org.chatwebsocket.consumer;

import org.chatwebsocket.dto.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(topics = "chat-topic")
    public void listen(ChatMessage message) {
        System.out.println("sending via kafka listener..");
        template.convertAndSendToUser(
            message.getReceiver(), "/chat_out/receive_message", message
        );
    }
}
