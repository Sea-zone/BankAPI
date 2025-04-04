package com.bank_system.services;


import com.bank_system.domain.Account;
import com.bank_system.domain.Transaction;
import com.bank_system.domain.TransactionType;
import com.bank_system.dto.TransactionDto;
import com.bank_system.dto.TransactionRequestDto;
import com.bank_system.repositories.AccountRepository;
import com.bank_system.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public TransactionServiceImpl( TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void create(TransactionRequestDto transactionDto) {
        Account account = accountRepository.findById(transactionDto.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + transactionDto.getAccountId()));

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(transactionDto.getAmount());
        transaction.setTransactionType(transactionDto.getTransactionType());
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Override
    public List<TransactionDto> getTransactionsByAccountId(Integer accountId) {
//        List<TransactionDto> transactionDtosList=new ArrayList<>();
//        List<Transaction> transactionsList=transactionRepository.findByAccountId(accountId);
//
//        for(Transaction t :transactionsList){
//           TransactionDto transactionDto= new TransactionDto();
//           transactionDto.setAccountId(t.getAccount().getId());
//           transactionDto.setTransactionType(t.getTransactionType());
//           transactionDto.setAmount(t.getAmount());
//           transactionDtosList.add(transactionDto);
//        }
//
//        return  transactionDtosList;

        return transactionRepository.findByAccountId(accountId).stream().map(transaction ->
                TransactionDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .transactionType(transaction.getTransactionType())
                        .amount(transaction.getAmount())
                        .build()).toList();

    }



    @Override
    public TransactionDto getTransactionById(Integer transactionId)
    {
        return new TransactionDto(transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id:" +transactionId)));
    }


    @Override
    public void update(Integer transacationId,TransactionDto transactionDto){
        Transaction transaction = transactionRepository.findById(transacationId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " +transacationId));
        if(transactionDto.getAmount() != null){
            transaction.setAmount(transactionDto.getAmount()



            );
        }
       if(transactionDto.getTransactionType() !=null) {
           transaction.setTransactionType(transactionDto.getTransactionType());
       }
        transactionRepository.save(transaction);
    }

    @Override
    public void delete(Integer transactionId){
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + transactionId));
    }

    @Override
    public List<TransactionDto> get(){
        return transactionRepository.findAll().stream().map(transaction ->
                TransactionDto.builder()
                        .accountId(transaction.getAccount().getId())
                        .id(transaction.getId())
                        .transactionType(transaction.getTransactionType())
                        .amount(transaction.getAmount())
                        .build()).toList();
    }

    @Override
    public List<Map<String, Object>> getTransactionAmountByCustomerId(Integer customerId) {
        return  transactionRepository.getTransactionAmountByCustomerId(customerId);
    }

}


