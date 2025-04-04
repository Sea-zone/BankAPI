package com.bank_system.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Integer  accountId;
    private String accountNumber;
    private String accountType; //savings ,checkings ,etc
    private Double balance;
    private Integer  customerId; //links acccount to a customer
}
