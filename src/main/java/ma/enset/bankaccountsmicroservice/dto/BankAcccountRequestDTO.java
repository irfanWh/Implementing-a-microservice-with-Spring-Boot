package ma.enset.bankaccountsmicroservice.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountsmicroservice.enums.AccountType;

import java.util.Date;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAcccountRequestDTO {
    private Double balance;
    private String currency;
    private AccountType type;
    private String customerId;
}
