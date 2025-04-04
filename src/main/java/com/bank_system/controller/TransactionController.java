package com.bank_system.controller;


import com.bank_system.dto.TransactionDto;
import com.bank_system.dto.TransactionRequestDto;
import com.bank_system.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/transaction")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public void create(@RequestBody TransactionRequestDto transactionDto) {
        transactionService.create(transactionDto);
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable Integer id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping()
    public List<TransactionDto> getAll() {
        return transactionService.get();
    }

    @GetMapping("/account/{accountId}")
    public List<TransactionDto> getByAccountId(@PathVariable Integer accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody TransactionDto transactionDto) {
        transactionService.update(id, transactionDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        transactionService.delete(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<Map<String, Object>> getTransactionByCustomerId(@PathVariable("customerId") Integer customerId) {
        return transactionService.getTransactionAmountByCustomerId(customerId);
    }

}
