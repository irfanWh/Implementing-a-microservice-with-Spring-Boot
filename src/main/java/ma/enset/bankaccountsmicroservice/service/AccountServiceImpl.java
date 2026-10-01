package ma.enset.bankaccountsmicroservice.service;

import ma.enset.bankaccountsmicroservice.dto.BankAcccountRequestDTO;
import ma.enset.bankaccountsmicroservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.entities.Customer;
import ma.enset.bankaccountsmicroservice.mappers.AccountMapper;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import ma.enset.bankaccountsmicroservice.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    private CustomerRepository customerRepository;
    @Autowired
    private AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAcccountRequestDTO bankAcccountRequestDTO){
        Customer customer = null;
        if (bankAcccountRequestDTO.getCustomerId() != null) {
            customer = customerRepository.findById(bankAcccountRequestDTO.getCustomerId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Customer %s not found", bankAcccountRequestDTO.getCustomerId())
                    ));
        }
        BankAcccount bankAcccount=BankAcccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAcccountRequestDTO.getBalance())
                .type(bankAcccountRequestDTO.getType())
                .currency(bankAcccountRequestDTO.getCurrency())
                .customer(customer)
                .build();
        BankAcccount saveBankAccount = bankAccountRepository.save(bankAcccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
