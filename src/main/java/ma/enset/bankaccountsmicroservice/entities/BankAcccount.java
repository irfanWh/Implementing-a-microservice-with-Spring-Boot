package ma.enset.bankaccountsmicroservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bankaccountsmicroservice.enums.AccountType;

import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BankAcccount {
    @Id
    private  String id;
    private Date createAt;
    private double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
}
