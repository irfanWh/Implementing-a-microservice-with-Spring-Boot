package ma.enset.bankaccountsmicroservice.repositories;

import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAcccount, String> {
}
