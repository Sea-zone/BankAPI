package com.bank_system.services;

import com.bank_system.domain.Account;
import com.bank_system.dto.AccountDto;

import java.util.List;
public interface AccountService {
    void create(AccountDto accountDto);
    List<AccountDto> getAccountsByCustomerId(Integer customerId);
    List<AccountDto> getAll();
    AccountDto getAccountById(Integer accountId);
    AccountDto getAccountByNumber(String accountNumber);
    void update(Integer accountId, AccountDto accountDto);
    void delete(Integer accountId);

    List<Account> get();
}
