package com.bank_system.services;


import com.bank_system.domain.Customer;
import com.bank_system.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    void create(CustomerDto customerDto);
    List<Customer> get();
    void update(Integer customerId, CustomerDto customerDto);
    void delete(Integer customerId);
}
