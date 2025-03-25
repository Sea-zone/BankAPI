package com.bank_system.services;


import com.bank_system.domain.Account;
import com.bank_system.domain.Transaction;
import com.bank_system.domain.TransactionType;
import com.bank_system.dto.TransactionDto;
import com.bank_system.repositories.AccountRepository;
import com.bank_system.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transcationRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void create(TransactionDto transactionDto) {
        Account account = accountRepository.findById(transactionDto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + transactionDto.getAccountId()));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(BigDecimal.valueOf(transactionDto.getAmount()));
        transaction.setTransactionType(TransactionType.valueOf(transactionDto.getTransactionType().toUpperCase()));
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(Integer accountId) {
        return transcationRepository.findByAccountId(accountId);
    }

    @Override
    public Transaction getTransactionById(Integer transactionId)
    {
        return transcationRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id:" +transactionId));
    }


    @Override
    public void update(Integer transacationId,TransactionDto transactionDto){
        Transaction transaction = transcationRepository.findById(transacationId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " +transacationId));
        if(transactionDto.getAmount() != null){
            transaction.setAmount(BigDecimal.valueOf(transactionDto.getAmount()));
        }
       if(transactionDto.getTransactionType() !=null) {
           transaction.setTransactionType(TransactionType.valueOf(transactionDto.getTransactionType().toUpperCase()));
       }
       transcationRepository.save(transaction);
    }

    @Override
    public void delete(Integer transactionId){
        Transaction transaction = transcationRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionId));
    }

    @Override
    public List<Transaction> get(){
        return transcationRepository.findAll();
    }
}


