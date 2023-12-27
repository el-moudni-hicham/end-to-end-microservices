package dev.moudni.accountservice.web;

import dev.moudni.accountservice.entites.Account;
import dev.moudni.accountservice.feign.CustomerRestClientFeign;
import dev.moudni.accountservice.model.Customer;
import dev.moudni.accountservice.repository.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountRepository accountRepository;
    private CustomerRestClientFeign customerRestClient;

    public AccountController(AccountRepository accountRepository, CustomerRestClientFeign customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts(){
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        });
        return accounts;
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable String id){
        Account account = accountRepository.findById(id).get();
        Customer customer = customerRestClient.getCustomerById(account.getCustomerId());
        account.setCustomer(customer);

        return account;
    }
}
