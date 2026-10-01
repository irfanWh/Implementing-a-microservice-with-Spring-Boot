package ma.enset.bankaccountsmicroservice;

import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.entities.Customer;
import ma.enset.bankaccountsmicroservice.enums.AccountType;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import ma.enset.bankaccountsmicroservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class MicroserviceForManagingBankAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceForManagingBankAccountsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            List<Customer> customers = List.of(
                    Customer.builder().id(UUID.randomUUID().toString()).name("Mohamed").email("mohamed@gmail.com").build(),
                    Customer.builder().id(UUID.randomUUID().toString()).name("Yassine").email("yassine@gmail.com").build(),
                    Customer.builder().id(UUID.randomUUID().toString()).name("Imane").email("imane@gmail.com").build()
            );
            customerRepository.saveAll(customers);
            for (int i = 0; i < 10; i++) {
                BankAcccount bankAcccount=BankAcccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .balance(10000+ Math.random()*90000)
                        .createAt(new Date())
                        .currency("MAD")
                        .customer(customers.get(i % customers.size()))
                        .build();
                bankAccountRepository.save(bankAcccount);
            }
        };
    }
}
