package ma.enset.bankaccountsmicroservice.service;

import ma.enset.bankaccountsmicroservice.dto.BankAcccountRequestDTO;
import ma.enset.bankaccountsmicroservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.mappers.AccountMapper;
import ma.enset.bankaccountsmicroservice.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public BankAccountResponseDTO addAccount(BankAcccountRequestDTO bankAcccountRequestDTO){
        BankAcccount bankAcccount=BankAcccount.builder()
                .id(UUID.randomUUID().toString())
                .createAt(new Date())
                .balance(bankAcccountRequestDTO.getBalance())
                .type(bankAcccountRequestDTO.getType())
                .currency(bankAcccountRequestDTO.getCurrency())
                .build();
        BankAcccount saveBankAccount = bankAccountRepository.save(bankAcccount);
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(saveBankAccount);
        return bankAccountResponseDTO;
    }
}
