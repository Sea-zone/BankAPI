package com.bank_system.services;

import com.bank_system.domain.Transaction;
import com.bank_system.dto.TransactionDto;


import java.util.List;
public interface TransactionService {
    void create(TransactionDto transactionDto);
    List<Transaction> getTransactionsByAccountId(Integer accountId);
    Transaction getTransactionById(Integer transactionId);
    void update(Integer transactionId, TransactionDto transactionDto);
    void delete(Integer transactionId);
    List<Transaction> get();
}