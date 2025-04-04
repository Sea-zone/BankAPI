package com.bank_system.dto;

import com.bank_system.domain.Transaction;
import com.bank_system.domain.TransactionType;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
public class TransactionRequestDto {

    private Integer accountId; //account involved in the transaction
    private BigDecimal amount;
    private TransactionType transactionType; // Deposit, withdrawl , transfer

    public TransactionRequestDto(Transaction transaction){
        this.accountId=transaction.getAccount().getId();
        this.amount=transaction.getAmount();
        this.transactionType=transaction.getTransactionType();
    }
}
