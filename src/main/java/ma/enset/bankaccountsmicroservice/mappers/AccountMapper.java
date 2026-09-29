package ma.enset.bankaccountsmicroservice.mappers;

import ma.enset.bankaccountsmicroservice.dto.BankAccountResponseDTO;
import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    public BankAccountResponseDTO fromBankAccount(BankAcccount bankAcccount){
        BankAccountResponseDTO bankAccountResponseDTO= new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAcccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
