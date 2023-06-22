package org.chatwebsocket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

public class ChatMessage {

    private String sender;

    private String receiver;

    private String message;

    private LocalDateTime localDateTime;

    public ChatMessage() {
        this.localDateTime = LocalDateTime.now();
    }

    public ChatMessage(String sender, String receiver, String message) {
        this();
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
            "sender='" + sender + '\'' +
            ", receiver='" + receiver + '\'' +
            ", message='" + message + '\'' +
            ", localDateTime=" + localDateTime +
            '}';
    }
}
