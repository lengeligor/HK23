package com.pegasus.hk.mapper;

import com.pegasus.hk.dto.TransactionDto;
import com.pegasus.hk.entity.Transaction;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-01T18:18:43+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class TransactionMapperImpl extends TransactionMapper {

    @Override
    public TransactionDto toTransactionDto(Transaction transaction) {
        if ( transaction == null ) {
            return null;
        }

        TransactionDto transactionDto = new TransactionDto();

        transactionDto.setId( transaction.getId() );
        transactionDto.setPersonId( transaction.getPersonId() );
        transactionDto.setDate( transaction.getDate() );
        transactionDto.setCategory( transaction.getCategory() );
        transactionDto.setSubcategory( transaction.getSubcategory() );
        transactionDto.setDescription( transaction.getDescription() );
        transactionDto.setAmount( transaction.getAmount() );
        transactionDto.setType( transaction.getType() );
        transactionDto.setBalanceBefore( transaction.getBalanceBefore() );
        transactionDto.setBalanceAfter( transaction.getBalanceAfter() );
        transactionDto.setLabel( transaction.getLabel() );

        return transactionDto;
    }
}
