package com.pegasus.hk.controller;

import com.pegasus.hk.model.request.MessageRequest;
import com.pegasus.hk.model.response.ChatGptResponse;
import com.pegasus.hk.client.BotClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/bot")
public class BotController {

    private final BotClient botClient;

    public BotController(BotClient botClient){
        this.botClient = botClient;
    }

    @PostMapping("/send")
    public ChatGptResponse sendMessage(@RequestBody MessageRequest message) {
        return botClient.askQuestion(message);
    }
}



