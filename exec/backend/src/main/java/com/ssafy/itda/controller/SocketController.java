package com.ssafy.itda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.spring.web.json.Json;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Controller
@CrossOrigin(origins = "*", allowCredentials="true",allowedHeaders = "*")
public class SocketController {

    @Autowired
    public SocketController() {

    }
    @MessageMapping("")
    @SendTo("")
    public String SocketHandler(@DestinationVariable String ip, String sttMessage) {
        System.out.println(sttMessage.getClass().getName());
        System.out.println(sttMessage);
        return sttMessage;
    }
}