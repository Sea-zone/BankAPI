package com.bank_system.services;

import com.bank_system.domain.Transaction;
import com.bank_system.dto.TransactionDto;
import com.bank_system.dto.TransactionRequestDto;
import com.bank_system.repositories.TransactionRepository;


import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TransactionService {
    void create(TransactionRequestDto transactionDto);
    List<TransactionDto> getTransactionsByAccountId(Integer accountId);
    TransactionDto getTransactionById(Integer transactionId);
    void update(Integer transactionId, TransactionDto transactionDto);
    void delete(Integer transactionId);
    List<TransactionDto> get();
    List<Map<String, Object>> getTransactionAmountByCustomerId(Integer customerId);
}