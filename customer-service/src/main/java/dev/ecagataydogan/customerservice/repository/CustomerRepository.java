package dev.ecagataydogan.customerservice.repository;

import dev.ecagataydogan.customerservice.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Long> {

    Optional<Customer> findByUserId(Long userId);
}
