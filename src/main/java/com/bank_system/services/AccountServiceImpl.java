package com.bank_system.services;


import com.bank_system.domain.Account;
import com.bank_system.domain.AccountType;
import com.bank_system.domain.Customer;
import com.bank_system.dto.AccountDto;
import com.bank_system.repositories.AccountRepository;
import com.bank_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void create(AccountDto accountDto){
        Customer customer = customerRepository.findById(accountDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + accountDto.getCustomerId()));
        Account account = new Account();
        account.setAccountNumber(generateAccountNumber());
        account.setCustomer(customer);
        account.setAccountType(AccountType.valueOf(accountDto.getAccountType()));
        account.setBalance(BigDecimal.valueOf(accountDto.getBalance()));

        accountRepository.save(account);
    }

    @Override
    public List<AccountDto> getAccountsByCustomerId(Integer customerId) {
        List<Account> accounts = accountRepository.findByCustomerId(customerId);

        return accounts.stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getAccountType().name(),
                        account.getBalance().doubleValue(),
                        account.getCustomer().getId().intValue()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<AccountDto> getAll() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountDto(
                        account.getId(),
                        account.getAccountNumber(),
                        account.getAccountType().name(),
                        account.getBalance().doubleValue(),
                        account.getCustomer().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));

        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType().name(),
                account.getBalance().doubleValue(),
                account.getCustomer().getId().intValue()
        );
    }

    @Override
    public AccountDto getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found with account number: " + accountNumber));

        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getAccountType().name(),
                account.getBalance().doubleValue(),
                account.getCustomer().getId().intValue()
        );
    }

    @Override
    public List<Account> get() {
        return accountRepository.findAll();
    }


    @Override
    public void update(Integer id,AccountDto accountDto){
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id" + id));
        if(accountDto.getAccountType() != null) {
            existingAccount.setAccountType(AccountType.valueOf(accountDto.getAccountType()));
        }
        if(accountDto.getBalance() != null){
            existingAccount.setBalance(BigDecimal.valueOf(accountDto.getBalance()));
        }

        accountRepository.save(existingAccount);

    }

    @Override
    public void delete(Integer id){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found with id: " + id));
        accountRepository.delete(account);
    }

    private String generateAccountNumber(){
        return "ACC" + System.currentTimeMillis();
    }

}
