package com.example.phoebus.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long accountId;

    @ManyToMany(mappedBy = "accountIds", fetch = FetchType.EAGER)
    private List<Customer> customerId = new ArrayList<>();

    private Integer accountNumber;

    public Account() {
    }

    public Account(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Customer> getCustomerList() {
        return customerId;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d, accountNumber='%s']",
                accountId, accountNumber);
    }

}
