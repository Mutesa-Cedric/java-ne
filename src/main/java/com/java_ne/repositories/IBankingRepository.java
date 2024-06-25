package com.java_ne.repositories;

import com.java_ne.models.Banking;
import com.java_ne.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IBankingRepository extends JpaRepository<Banking,Long> {
    List<Banking> findBankingByCustomer(Customer customer);
}
