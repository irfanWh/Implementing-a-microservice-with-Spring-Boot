package ma.enset.bankaccountsmicroservice.repositories;

import ma.enset.bankaccountsmicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
