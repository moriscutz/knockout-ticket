package com.knockoutticket.backend.controller;

import com.knockoutticket.backend.domain.models.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/sendNotification")
    @SendTo("/topic/notifications")
    public NotificationMessage send(NotificationMessage message) {
        return message;
    }

    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", new NotificationMessage(message));
    }
}
