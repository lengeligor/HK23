package com.pegasus.hk.controller;

import com.pegasus.hk.dto.PersonDto;
import com.pegasus.hk.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
@CrossOrigin
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(@NonNull PersonService personService){
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getPerson(@PathVariable Long id){
        return ResponseEntity.ok(personService.getPerson(id));
    }
}
