package com.java_ne.repositories;

import com.java_ne.models.Customer;
import com.java_ne.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByCustomer(Customer customer);
}
