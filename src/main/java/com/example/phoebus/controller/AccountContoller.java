package com.example.phoebus.controller;

import com.example.phoebus.model.Account;
import com.example.phoebus.repository.AccountRepository;
import com.example.phoebus.model.Customer;
import com.example.phoebus.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountContoller {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/addAccount")
    public String addAccount(@RequestParam(value = "customerId") Long customerId, @RequestParam(value = "accountNumber") Integer accountNumber) {
        // check if the customer exists
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer == null) {
            return "Customer does not exist";
        }
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            account = new Account(accountNumber);
        } else if (account.getCustomerList().contains(customer)) {
            //reject if the account number already exists for the same user
            return "Account already exist for this Customer";
        }
        // add the account to the customer
        customer.addAccount(account);
        accountRepository.save(account);
        customerRepository.save(customer);
        return "Account " + accountNumber + " added to customer " + customerId;
    }

    @GetMapping("/customersByAccountNumber")
    public String customersByAccountNumber(@RequestParam(value = "accountNumber") Integer accountNumber) {
        StringBuilder sb = new StringBuilder();
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if ( account != null ) {
            for (Customer record : account.getCustomerList()) {
                sb.append(record.toString());
                sb.append("\n");
            }
            return sb.toString();
        } else {
            return "Account not found!";
        }
    }
}
