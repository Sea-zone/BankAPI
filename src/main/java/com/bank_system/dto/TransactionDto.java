package com.bank_system.dto;

import com.bank_system.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Integer accountId; //account involved in the transaction
    private BigDecimal amount;
    private TransactionType transactionType; // Deposit, withdrawl , transfer
}
