package com.bank_system.services;


import com.bank_system.domain.Customer;
import com.bank_system.dto.CustomerDto;
import com.bank_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void create(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNo(customerDto.getPhoneNo());
        customer.setAddress(customerDto.getAddress());
        customerRepository.save(customer);
    }
    @Override
    public List<Customer> get() {
        return  customerRepository.findAll();
    }

    @Override
    public void update(Integer id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        if (customerDto.getFirstName() != null) {
            existingCustomer.setFirstName(customerDto.getFirstName());
        }
        if (customerDto.getLastName() != null) {
            existingCustomer.setLastName(customerDto.getLastName());
        }
        if (customerDto.getEmail() != null) {
            existingCustomer.setEmail(customerDto.getEmail());
        }
        if (customerDto.getPhoneNo() != null) {
            existingCustomer.setPhoneNo(customerDto.getPhoneNo());
        }
        if (customerDto.getAddress() != null) {
            existingCustomer.setAddress(customerDto.getAddress());
        }
        customerRepository.save(existingCustomer);
    }

    @Override
    public void delete(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }
}
