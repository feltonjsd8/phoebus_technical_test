package com.example.phoebus.controller;

import com.example.phoebus.model.Account;
import com.example.phoebus.model.Customer;
import com.example.phoebus.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerContoller {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/accountsByCustomerId")
    public String accountsByCustomerId(@RequestParam(value = "customerId") Long customerId) {
        StringBuilder sb = new StringBuilder();
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer != null) {
            for (Account record : customer.getAccountIdList()) {
                sb.append(record.toString());
                sb.append("\n");
            }
            return sb.toString();
        } else {
            return "Customer not found";
        }
    }
}
