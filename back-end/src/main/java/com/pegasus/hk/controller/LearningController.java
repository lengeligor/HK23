package com.pegasus.hk.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pegasus.hk.model.request.MessageRequest;
import com.pegasus.hk.service.LearningService;

import lombok.NonNull;

@RestController
@CrossOrigin
@RequestMapping("/learn")
public class LearningController {

    private final LearningService learningService;

    public LearningController(@NonNull LearningService learningService){
        this.learningService = learningService;
    }

    @GetMapping("/get-themes")
    public ResponseEntity<List<String>> getThemes(){
        return ResponseEntity.ok(learningService.getThemes());
    }

    @GetMapping()
    public ResponseEntity<MessageRequest> getThemeMaterial(@RequestParam String theme){
        return ResponseEntity.ok(learningService.getThemeMaterial(theme));
    }
}
