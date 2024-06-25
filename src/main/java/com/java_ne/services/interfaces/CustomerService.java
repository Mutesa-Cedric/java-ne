package com.java_ne.services.interfaces;

import com.java_ne.dtos.customer.CreateUpdateCustomer;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomerService {

    public ResponseEntity<ApiResponse<Customer>> createCustomer(CreateUpdateCustomer customer);
    public ResponseEntity<ApiResponse<Customer>> updateCustomer(CreateUpdateCustomer customer , Long customerId);
    public ResponseEntity<ApiResponse<Customer>> deleteCustomer(Long customerId);
    public ResponseEntity<ApiResponse<Customer>> getCustomer(Long customerId);
    public ResponseEntity<ApiResponse<Customer>> getCustomerByMobile(String mobile);
    public ResponseEntity<ApiResponse<Customer>> getCustomerByEmail(String email);
    public ResponseEntity<ApiResponse<Customer>> getCustomerByAccount(String account);
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers();

}
