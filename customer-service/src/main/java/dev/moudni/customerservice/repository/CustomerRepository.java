package dev.moudni.customerservice.repository;

import dev.moudni.customerservice.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameContains(String keyword);
    List<Customer> findByFirstNameOrLastNameContaining(String fn, String ln);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.email=?1")
    Boolean checkIfEmailExists(String email);
}
