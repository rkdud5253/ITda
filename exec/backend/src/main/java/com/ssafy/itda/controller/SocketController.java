package com.ssafy.itda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
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
    @MessageMapping("/socket/{ip}/receive")
    @SendTo("/socket/{ip}/send")
    public String SocketHandler(@DestinationVariable String ip, String sttMessage) {
        System.out.println(1);
        return sttMessage;
    }
}