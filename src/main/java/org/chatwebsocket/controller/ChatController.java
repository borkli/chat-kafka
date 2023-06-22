package org.chatwebsocket.controller;

import org.chatwebsocket.dto.ChatMessage;
import org.chatwebsocket.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class ChatController {

    private final SimpMessagingTemplate template;
    private final ChatService chatService;

    public ChatController(SimpMessagingTemplate template, ChatService chatService) {
        this.template = template;
        this.chatService = chatService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @PostMapping(value = "/send")
    @ResponseBody
    public ResponseEntity<HttpStatus> sendMessage(@RequestBody ChatMessage message) {
            chatService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/send_message")
    public ChatMessage messageReceiver(@Payload ChatMessage message) {
        return message;
    }

//    @GetMapping("/history")
//    @ResponseBody
//    public List<String> chatHistory() {
//        return chatService.getChatHistory();
//    }
}
