package com.example.phoebus.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long customerId;

    // although the instructions ask us to maintain a list of strings ids I think it's better to map a bidirectional many to many relationship here of
    // customers and accounts objects. The id can still be retrieved from the object if required.
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_account",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accountIds = new ArrayList<>();

    public List<Account> getAccountIdList() {
        return accountIds;
    }

    public Customer() {
    }

    public void addAccount(Account account) {
        accountIds.add(account);
        account.getCustomerList().add(this);
    }

    private String forename;
    private String surname;
    private Date dateOfBirth;

    public Customer(String firstName, String lastName, Date dateOfBirth) {
        this.forename = firstName;
        this.surname = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, forename='%s', surname='%s', dateOfBirth=%tc]",
                customerId, forename, surname, dateOfBirth);
    }
}
