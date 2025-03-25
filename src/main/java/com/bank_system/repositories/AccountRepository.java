package com.bank_system.repositories;

import com.bank_system.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomerId(Integer customerId);  // Correct way to find by customer ID

    Optional<Account> findByAccountNumber(String accountNumber);  // To retrieve an account by number
}

