package ma.enset.bankaccountsmicroservice.service;

import ma.enset.bankaccountsmicroservice.dto.BankAcccountRequestDTO;
import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import ma.enset.bankaccountsmicroservice.dto.BankAccountResponseDTO;

public interface AccountService {
    public BankAccountResponseDTO addAccount(BankAcccountRequestDTO bankAcccountDTO);
}
