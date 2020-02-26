package com.example.phoebus.repository;

import com.example.phoebus.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByAccountNumber(Integer accountNumber);
}
