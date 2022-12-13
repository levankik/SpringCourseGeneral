package ge.workshops.workshop1.repository;



import ge.workshops.workshop1.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.deleted = false")
    List<Customer> findActive();
}

