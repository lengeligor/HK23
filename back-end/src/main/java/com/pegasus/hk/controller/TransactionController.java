package com.pegasus.hk.controller;

import java.util.List;

import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;

@RestController
@CrossOrigin
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(@NonNull TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<TransactionDto>> getPersonTransactions(@PathVariable Long id){
        return ResponseEntity.ok(transactionService.getPersonTransactions(id));
    }
}
