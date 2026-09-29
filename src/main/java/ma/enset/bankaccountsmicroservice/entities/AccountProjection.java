package ma.enset.bankaccountsmicroservice.entities;

import ma.enset.bankaccountsmicroservice.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = BankAcccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
}
