package ma.enset.bankaccountsmicroservice.repositories;

import ma.enset.bankaccountsmicroservice.entities.BankAcccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(
        path = "bankAccounts",
        collectionResourceRel = "bankAccounts"
)
public interface BankAccountRepository extends JpaRepository<BankAcccount, String> {
    List<BankAcccount> findByCustomerId(String customerId);
}
