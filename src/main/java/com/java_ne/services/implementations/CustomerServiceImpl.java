package com.java_ne.services.implementations;

import com.java_ne.dtos.customer.CreateUpdateCustomer;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.ConflictException;
import com.java_ne.exceptions.CustomException;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.models.Customer;
import com.java_ne.repositories.ICustomerRepository;
import com.java_ne.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    public final ICustomerRepository customerRepository;

    @Override
    public ResponseEntity<ApiResponse<Customer>> createCustomer(CreateUpdateCustomer customer) {
        try {
            Optional<Customer> existingCustomerMobile = customerRepository.findCustomerByMobile(customer.getMobile());
            if (existingCustomerMobile.isPresent()) {
                throw new ConflictException("Customer with mobile number already exists");
            }
            Optional<Customer> existingCustomerEmail = customerRepository.findCustomerByEmail(customer.getEmail());
            if (existingCustomerEmail.isPresent()) {
                throw new ConflictException("Customer with email already exists");
            }
            Optional<Customer> existingCustomerAccount = customerRepository.findCustomerByAccount(customer.getAccount());
            if (existingCustomerAccount.isPresent()) {
                throw new ConflictException("Customer with account already exists");
            }
            Customer newCustomer = new Customer();
            newCustomer.setAccount(customer.getAccount());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setMobile(customer.getMobile());
            newCustomer.setFirstName(customer.getFirstName());
            newCustomer.setLastName(customer.getLastName());
            newCustomer.setBalance(customer.getBalance());
            newCustomer.setDob(customer.getDob());

            return ApiResponse.success("Customer created successfully", HttpStatus.CREATED, customerRepository.save(newCustomer));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(CreateUpdateCustomer customer, Long customerId) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            Customer customer1 = existingCustomer.get();
            //find if new credentials already exist
            Optional<Customer> existingCustomerMobile = customerRepository.findCustomerByMobile(customer.getMobile());
            if (existingCustomerMobile.isPresent() && !existingCustomerMobile.get().getId().equals(customerId)) {
                throw new ConflictException("Customer with mobile number already exists");
            }
            Optional<Customer> existingCustomerEmail = customerRepository.findCustomerByEmail(customer.getEmail());
            if (existingCustomerEmail.isPresent() && !existingCustomerEmail.get().getId().equals(customerId)) {
                throw new ConflictException("Customer with email already exists");
            }
            Optional<Customer> existingCustomerAccount = customerRepository.findCustomerByAccount(customer.getAccount());
            if (existingCustomerAccount.isPresent() && !existingCustomerAccount.get().getId().equals(customerId)) {
                throw new ConflictException("Customer with account already exists");
            }
            customer1.setAccount(customer.getAccount());
            customer1.setEmail(customer.getEmail());
            customer1.setMobile(customer.getMobile());
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setBalance(customer.getBalance());
            customer1.setDob(customer.getDob());
            return ApiResponse.success("Customer updated successfully", HttpStatus.OK, customerRepository.save(customer1));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
    @Override
    public ResponseEntity<ApiResponse<Customer>> deleteCustomer(Long customerId) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            customerRepository.delete(existingCustomer.get());
            return ApiResponse.success("Customer deleted successfully", HttpStatus.OK, existingCustomer.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Customer>> getCustomer(Long customerId) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            return ApiResponse.success("Customer retrieved successfully", HttpStatus.OK, existingCustomer.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Customer>> getCustomerByMobile(String mobile) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findCustomerByMobile(mobile);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            return ApiResponse.success("Customer retrieved successfully", HttpStatus.OK, existingCustomer.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Customer>> getCustomerByEmail(String email) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findCustomerByEmail(email);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            return ApiResponse.success("Customer retrieved successfully", HttpStatus.OK, existingCustomer.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Customer>> getCustomerByAccount(String account) {
        try {
            Optional<Customer> existingCustomer = customerRepository.findCustomerByAccount(account);
            if (existingCustomer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            return ApiResponse.success("Customer retrieved successfully", HttpStatus.OK, existingCustomer.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        try {
            return ApiResponse.success("Customers retrieved successfully", HttpStatus.OK, customerRepository.findAll());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
