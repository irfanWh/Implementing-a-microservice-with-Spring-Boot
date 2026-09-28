package ma.enset.bankaccountsmicroservice;

import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.enums.AccountType;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class MicroserviceForManagingBankAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceForManagingBankAccountsApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository){
        return args -> {
            for (int i = 0; i < 10; i++) {
                BankAcccount bankAcccount=BankAcccount.builder()
                        .id(UUID.randomUUID().toString())
                        .type(Math.random()>0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .balance(10000+ Math.random()*90000)
                        .createAt(new Date())
                        .currency("MAD")
                        .build();
                bankAccountRepository.save(bankAcccount);
            }
        };
    }
}
