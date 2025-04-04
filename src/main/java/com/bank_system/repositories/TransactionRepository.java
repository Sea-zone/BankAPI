package com.bank_system.repositories;

import com.bank_system.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transactions  where account_id=:accountId",nativeQuery = true)
    List<Transaction> findByAccountId(Integer accountId);

    @Query(value = "SELECT sum(t.amount),t.transaction_type FROM transactions t\n" +
            "INNER JOIN accounts a on t.account_id=a.id\n" +
            "inner join  customers c on c.id = a.customer_id\n" +
            "WHERE c.id = :customerId\n" +
            "group by  t.transaction_type",nativeQuery = true)
    List<Map<String, Object>> getTransactionAmountByCustomerId(@Param("customerId") Integer customerId);
}

