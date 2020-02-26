package com.example.phoebus.repository;

import com.example.phoebus.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByCustomerId(Long customerId);
}
