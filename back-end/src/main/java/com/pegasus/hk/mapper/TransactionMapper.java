package com.pegasus.hk.mapper;

import org.mapstruct.Mapper;

import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.entity.Transaction;

@Mapper(componentModel = "spring")
public abstract class TransactionMapper {

    public abstract TransactionDto toTransactionDto(Transaction transaction);
}
