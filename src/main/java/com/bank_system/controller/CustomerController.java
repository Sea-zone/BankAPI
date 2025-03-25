package com.bank_system.controller;


import com.bank_system.domain.Customer;
import com.bank_system.dto.CustomerDto;
import com.bank_system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customers")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    void create(@RequestBody CustomerDto customerDto){
        customerService.create(customerDto);
    }
    @GetMapping
    List<Customer> get(){
        return customerService.get();
    }


    @PutMapping("/{id}")
    void update(@PathVariable Integer id, @RequestBody CustomerDto customerDto){
        customerService.update(id, customerDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        customerService.delete(id);
    }
}