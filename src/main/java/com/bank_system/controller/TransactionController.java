package com.bank_system.controller;


import com.bank_system.dto.TransactionDto;
import com.bank_system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
@RestController

public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void create(@RequestBody TransactionDto transactionDto){
        transactionService.create(transactionDto);
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/account/{accountId}")
    public TransactionDto getByAccountId(@PathVariable Integer accountId){
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id,@RequestBody TransactionDto transactionDto){
        transactionService.update(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        transactionService.delete(id);
    }


}
