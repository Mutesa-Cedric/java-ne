package com.java_ne.repositories;

import com.java_ne.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ICustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findCustomerByMobile(String mobile);
    Optional<Customer> findCustomerByEmail(String email);
    Optional<Customer> findCustomerByAccount(String account);
}
