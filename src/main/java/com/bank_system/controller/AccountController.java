package com.bank_system.controller;

import com.bank_system.dto.AccountDto;
import com.bank_system.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/accounts")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public void create(@RequestBody AccountDto accountDto) {
        accountService.create(accountDto);
    }

    @GetMapping("/customer/{customerId}")
    public List<AccountDto> getByCustomerId(@PathVariable Integer customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }
    @GetMapping
    public List<AccountDto> getAllCustomer() {
        return accountService.getAll();
    }
    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable Integer id) {
        return accountService.getAccountById(id);
    }

    @GetMapping("/number/{accountNumber}")
    public AccountDto getByNumber(@PathVariable String accountNumber) {
        return accountService.getAccountByNumber(accountNumber);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody AccountDto accountDto) {
        accountService.update(id, accountDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}
