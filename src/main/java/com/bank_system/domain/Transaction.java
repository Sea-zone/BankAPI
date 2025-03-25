package com.bank_system.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_gen")
    @SequenceGenerator(name = "transaction_seq_gen",sequenceName = "transaction_seq",allocationSize = 1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "account_id",nullable = false)
    private Account account;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;


    @Column(nullable = false)
    private LocalDateTime timestamp;

}