package dev.amary.accountservice.web;

import dev.amary.accountservice.clients.CustomerRestClient;
import dev.amary.accountservice.entities.BankAccount;
import dev.amary.accountservice.model.Customer;
import dev.amary.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    private BankAccountRepository bankAccountRepository;
    private CustomerRestClient customerRestClient;

    public AccountController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        bankAccounts.forEach(acc -> {
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });

        return bankAccounts;
    }

    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){

        BankAccount bankAccount = bankAccountRepository.findById(id).get();
        Customer customer = customerRestClient.findCustomerById(bankAccount.getCustomerId());
        bankAccount.setCustomer(customer);

        return bankAccount;

    }

}
