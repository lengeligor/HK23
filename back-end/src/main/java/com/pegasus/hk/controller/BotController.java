package com.pegasus.hk.controller;

import java.util.ArrayList;
import java.util.List;

import com.pegasus.hk.model.request.MessageRequest;
import com.pegasus.hk.model.response.ChatGptResponse;
import com.pegasus.hk.client.BotClient;
import com.pegasus.hk.model.response.Choice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

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
        try {
            return botClient.askQuestion(message);
        } catch (
                HttpClientErrorException e) {
            ChatGptResponse response = new ChatGptResponse();
            List<Choice> choices = new ArrayList<>();
            Choice choice = new Choice();
            choice.setText("Our services are temporary unavailable. Try later.");
            choices.add(choice);
            response.setChoices(choices);
            return response;
        }
    }
}



