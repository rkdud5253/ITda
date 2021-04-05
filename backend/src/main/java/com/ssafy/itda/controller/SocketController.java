package com.ssafy.itda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(origins = "*", allowCredentials="true",allowedHeaders = "*")
public class SocketController {

    @Autowired
    public SocketController() {

    }
    @MessageMapping("/socket/receive")
    @SendTo("/socket/send")
    public String SocketHandler(String sttMessage) {
        System.out.println(1);
        return sttMessage;
    }
}