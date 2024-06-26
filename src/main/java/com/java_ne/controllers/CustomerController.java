package com.java_ne.controllers;

import com.java_ne.dtos.customer.CreateUpdateCustomer;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Customer;
import com.java_ne.services.interfaces.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@Valid @RequestBody CreateUpdateCustomer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(@Valid @RequestBody CreateUpdateCustomer customer, @PathVariable Long customerId) {
        return customerService.updateCustomer(customer, customerId);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }

    @GetMapping("/id/{customerId}")
    public ResponseEntity<ApiResponse<Customer>> getCustomer(@PathVariable Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}