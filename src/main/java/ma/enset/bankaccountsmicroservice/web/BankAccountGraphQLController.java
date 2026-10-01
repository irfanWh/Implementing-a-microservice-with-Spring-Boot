package ma.enset.bankaccountsmicroservice.web;

import ma.enset.bankaccountsmicroservice.dto.BankAcccountRequestDTO;
import ma.enset.bankaccountsmicroservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountsmicroservice.dto.CustomerRequestDTO;
import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.entities.Customer;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import ma.enset.bankaccountsmicroservice.repositories.CustomerRepository;
import ma.enset.bankaccountsmicroservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class BankAccountGraphQLController {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping
    public List<BankAcccount> accountsList() {
        return bankAccountRepository.findAll();
    }
    @QueryMapping
    public BankAcccount bankAccountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                String.format("Account %s not found", id)
                        )
                );
    }

    @QueryMapping
    public List<Customer> customersList() {
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));
    }

    @SchemaMapping(typeName = "Customer", field = "bankAccounts")
    public List<BankAcccount> bankAccounts(Customer customer) {
        return bankAccountRepository.findByCustomerId(customer.getId());
    }

    @MutationMapping
    public BankAccountResponseDTO addBankAccount(@Argument BankAcccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }

    @MutationMapping
    public BankAcccount updateBankAccount(@Argument String id, @Argument BankAcccountRequestDTO bankAccount) {
        BankAcccount acccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        if (bankAccount.getBalance() != null) acccount.setBalance(bankAccount.getBalance());
        if (bankAccount.getCurrency() != null) acccount.setCurrency(bankAccount.getCurrency());
        if (bankAccount.getType() != null) acccount.setType(bankAccount.getType());
        if (bankAccount.getCustomerId() != null) {
            Customer customer = customerRepository.findById(bankAccount.getCustomerId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Customer %s not found", bankAccount.getCustomerId())
                    ));
            acccount.setCustomer(customer);
        }
        return bankAccountRepository.save(acccount);
    }

    @MutationMapping
    public Boolean deleteBankAccount(@Argument String id) {
        bankAccountRepository.deleteById(id);
        return true;
    }

    @MutationMapping
    public Customer addCustomer(@Argument CustomerRequestDTO customer) {
        Customer newCustomer = Customer.builder()
                .id(UUID.randomUUID().toString())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
        return customerRepository.save(newCustomer);
    }

    @MutationMapping
    public Customer updateCustomer(@Argument String id, @Argument CustomerRequestDTO customer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));
        if (customer.getName() != null) existingCustomer.setName(customer.getName());
        if (customer.getEmail() != null) existingCustomer.setEmail(customer.getEmail());
        return customerRepository.save(existingCustomer);
    }

    @MutationMapping
    public Boolean deleteCustomer(@Argument String id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Customer %s not found", id)));
        List<BankAcccount> bankAccounts = bankAccountRepository.findByCustomerId(id);
        bankAccounts.forEach(bankAccount -> bankAccount.setCustomer(null));
        bankAccountRepository.saveAll(bankAccounts);
        customerRepository.deleteById(id);
        return true;
    }
}
