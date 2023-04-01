package com.pegasus.hk.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.entity.QTransaction;
import com.pegasus.hk.exceptions.BusinessException;
import com.pegasus.hk.mapper.TransactionMapper;
import com.pegasus.hk.repository.TransactionRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(@NonNull TransactionRepository transactionRepository,
                              @NonNull TransactionMapper transactionMapper){
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
    }

    public List<TransactionDto> getPersonTransactions(Long id) {
        return transactionRepository.findAll(QTransaction.transaction.personId.eq(id), Pageable.unpaged()).stream().map(transactionMapper::toTransactionDto).collect(Collectors.toList());
    }
}
