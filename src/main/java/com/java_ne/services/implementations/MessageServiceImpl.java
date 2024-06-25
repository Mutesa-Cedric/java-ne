package com.java_ne.services.implementations;

import com.java_ne.dtos.message.CreateUpdateMessage;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.CustomException;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.models.Customer;
import com.java_ne.models.Message;
import com.java_ne.repositories.ICustomerRepository;
import com.java_ne.repositories.IMessageRepository;
import com.java_ne.services.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    public final IMessageRepository messageRepository;
    public final ICustomerRepository customerRepository;

    @Override
    public void createMessage(CreateUpdateMessage message) {
        try {
            Message newMessage = new Message();
            Customer customer = customerRepository.findById(message.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));
            newMessage.setCustomer(customer);
            newMessage.setMessage(message.getMessage());
            messageRepository.save(newMessage);
            System.out.println("Message created successfully");
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Message>> updateMessage(CreateUpdateMessage message, Long messageId) {
        try {
            Optional<Message> existingMessage = messageRepository.findById(messageId);
            if (existingMessage.isEmpty()) {
                throw new NotFoundException("Message not found");
            }
            Message updatedMessage = existingMessage.get();
            updatedMessage.setMessage(message.getMessage());
            messageRepository.save(updatedMessage);
            return ApiResponse.success("Message updated successfully", HttpStatus.OK, updatedMessage);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Message>>> getAllMessages() {
        try {
            return ApiResponse.success("Messages found", HttpStatus.OK, messageRepository.findAll());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Message>> getMessage(Long messageId) {
        try {
            Optional<Message> message = messageRepository.findById(messageId);
            if (message.isEmpty()) {
                throw new NotFoundException("Message not found");
            }
            return ApiResponse.success("Message found", HttpStatus.OK, message.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Message>>> getMessagesByCustomer(Long customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if (customer.isEmpty()) {
                throw new NotFoundException("Customer not found");
            }
            return ApiResponse.success("Messages found", HttpStatus.OK, messageRepository.findMessagesByCustomer(customer.get()));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Message>> deleteMessage(Long messageId) {
        try {
            Optional<Message> message = messageRepository.findById(messageId);
            if (message.isEmpty()) {
                throw new NotFoundException("Message not found");
            }
            messageRepository.delete(message.get());
            return ApiResponse.success("Message deleted successfully", HttpStatus.OK, message.get());

        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
