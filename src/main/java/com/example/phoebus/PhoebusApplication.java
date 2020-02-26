package com.example.phoebus;

import com.example.phoebus.model.Account;
import com.example.phoebus.model.Customer;
import com.example.phoebus.repository.AccountRepository;
import com.example.phoebus.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


@SpringBootApplication
public class PhoebusApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(PhoebusApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(PhoebusApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Override
	@Transactional
	public void run(String... strings) {
		// save a few customers
		Customer customer = new Customer("John", "Thomas", new GregorianCalendar(1986, Calendar.FEBRUARY, 12).getTime());
		Customer customer2 = new Customer("David", "Davis", new GregorianCalendar(1974, Calendar.DECEMBER, 9).getTime());
		Customer customer3 = new Customer("Richard", "Edwards", new GregorianCalendar(1965, Calendar.OCTOBER, 30).getTime());
		Customer customer4 = new Customer("Nigel", "Wallace", new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime());
		Customer customer5 = new Customer("Lee", "Underhill", new GregorianCalendar(1934, Calendar.AUGUST, 22).getTime());
		customerRepository.save(customer);
		customerRepository.save(customer2);
		customerRepository.save(customer3);
		customerRepository.save(customer4);
		customerRepository.save(customer5);

		//save a few accounts
		Account account = new Account(123);
		Account account2 = new Account(456);
		Account account3 = new Account(789);
		Account account4 = new  Account(012);
		accountRepository.save(account);
		accountRepository.save(account2);
		accountRepository.save(account3);
		accountRepository.save(account4);

		// add a few customer accounts
		customer.addAccount(account);
		customer2.addAccount(account);
		customer.addAccount(account2);
		customer3.addAccount(account4);
		customer4.addAccount(account3);

		// fetch all customers
		log.info("Customers found with findAll():");
		log.info("-------------------------------");
		for (Customer record : customerRepository.findAll()) {
			log.info(record.toString());
		}
		log.info("--------------------------------------------");

		// fetch all accounts
		log.info("Accounts found with findAll():");
		log.info("-------------------------------");
		for (Account record : accountRepository.findAll()) {
			log.info(record.toString());
		}
		log.info("--------------------------------------------");

		// findByAccountNumber
		final Integer accountNumber = 123;
		log.info("Customer with findByAccountNumber: " + accountNumber);
		log.info("--------------------------------");
		for (Customer record : accountRepository.findByAccountNumber(accountNumber).getCustomerList()) {
			log.info(record.toString());
		}
		log.info("");

		// findByCustomerId
		final Long customerId = 1L;
		log.info("Accounts with findByCustomerId: " + customerId);
		log.info("--------------------------------------------");
		for (Account record : customerRepository.findByCustomerId(customerId).getAccountIdList()) {
			log.info(record.toString());
		}
		log.info("--------------------------------------------");
	}
}
