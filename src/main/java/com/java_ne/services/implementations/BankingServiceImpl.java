package com.java_ne.services.implementations;

import com.java_ne.dtos.banking.CreateUpdateBanking;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.enumerations.banking.EBankingType;
import com.java_ne.exceptions.BadRequestException;
import com.java_ne.exceptions.CustomException;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.models.Banking;
import com.java_ne.models.Customer;
import com.java_ne.repositories.IBankingRepository;
import com.java_ne.repositories.ICustomerRepository;
import com.java_ne.services.interfaces.BankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankingServiceImpl implements BankingService {

    private final IBankingRepository bankingRepository;
    private final ICustomerRepository customerRepository;

    @Override
    public ResponseEntity<ApiResponse<Banking>> createBanking(CreateUpdateBanking banking) {
        try {
            Banking banking1 = new Banking();
            Customer customer = customerRepository.findById(banking.getCustomer().getId()).orElseThrow(() -> new NotFoundException("Customer not found"));
            banking1.setCustomer(customer);
            banking1.setAccount(banking.getAccount());
            banking1.setAmount(banking.getAmount());
            banking1.setType(banking.getType());

            //update customer balance according to banking type
            if (banking.getType().equals(EBankingType.SAVING)) {
                customer.setBalance(customer.getBalance() + banking.getAmount());
            } else if (banking.getType().equals(EBankingType.WITHDRAW)) {
                if (customer.getBalance() < banking.getAmount()) {
                    throw new BadRequestException("Customer balance is less than the amount to withdraw");
                }
                customer.setBalance(customer.getBalance() - banking.getAmount());
            } else if (banking.getType().equals(EBankingType.TRANSFER)) {
                if (customer.getBalance() < banking.getAmount()) {
                    throw new BadRequestException("Customer balance is less than the amount to transfer");
                }
                Optional<Customer> accountToTransfer = customerRepository.findCustomerByAccount(banking.getAccount());
                if (accountToTransfer.isEmpty()) {
                    throw new NotFoundException("Account to transfer not found");
                }
                customer.setBalance(customer.getBalance() - banking.getAmount());
                accountToTransfer.get().setBalance(accountToTransfer.get().getBalance() + banking.getAmount());
                customerRepository.save(accountToTransfer.get());
            }
            customerRepository.save(customer);
            return ApiResponse.success("Banking created successfully", HttpStatus.CREATED, bankingRepository.save(banking1));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Banking>> updateBanking(CreateUpdateBanking banking, Long bankingId) {
        try {
            Optional<Banking> existingBanking = bankingRepository.findById(bankingId);
            if (existingBanking.isEmpty()) {
                throw new NotFoundException("Banking does not exist");
            }
            Banking banking1 = existingBanking.get();
            Customer customer = customerRepository.findById(banking.getCustomer().getId()).orElseThrow(() -> new NotFoundException("Customer not found"));
            banking1.setCustomer(customer);
            banking1.setAccount(banking.getAccount());
            banking1.setAmount(banking.getAmount());
            banking1.setType(banking.getType());

            //update customer balance according to banking type
            if (banking.getType().equals(EBankingType.SAVING)) {
                customer.setBalance(customer.getBalance() + banking.getAmount());
            } else if (banking.getType().equals(EBankingType.WITHDRAW)) {
                if (customer.getBalance() < banking.getAmount()) {
                    throw new BadRequestException("Customer balance is less than the amount to withdraw");
                }
                customer.setBalance(customer.getBalance() - banking.getAmount());
            } else if (banking.getType().equals(EBankingType.TRANSFER)) {
                if (customer.getBalance() < banking.getAmount()) {
                    throw new BadRequestException("Customer balance is less than the amount to transfer");
                }
                Optional<Customer> accountToTransfer = customerRepository.findCustomerByAccount(banking.getAccount());
                if (accountToTransfer.isEmpty()) {
                    throw new NotFoundException("Account to transfer not found");
                }
                customer.setBalance(customer.getBalance() - banking.getAmount());
                accountToTransfer.get().setBalance(accountToTransfer.get().getBalance() + banking.getAmount());
                customerRepository.save(accountToTransfer.get());
            }
            customerRepository.save(customer);
            return ApiResponse.success("Banking updated successfully", HttpStatus.OK, bankingRepository.save(banking1));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Banking>> deleteBanking(Long bankingId) {
        try {
            Optional<Banking> existingBanking = bankingRepository.findById(bankingId);
            if (existingBanking.isEmpty()) {
                throw new NotFoundException("Banking does not exist");
            }
            bankingRepository.deleteById(bankingId);
            return ApiResponse.success("Banking deleted successfully", HttpStatus.OK, existingBanking.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Banking>> getBanking(Long bankingId) {
        try {
            Optional<Banking> existingBanking = bankingRepository.findById(bankingId);
            if (existingBanking.isEmpty()) {
                throw new NotFoundException("Banking does not exist");
            }
            return ApiResponse.success("Banking retrieved successfully", HttpStatus.OK, existingBanking.get());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<Banking>>> getBankingByCustomer(Long customerId) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerId);
            if (customer.isEmpty()) {
                throw new NotFoundException("Customer does not exist");
            }
            return ApiResponse.success("Banking retrieved successfully", HttpStatus.OK, bankingRepository.findBankingByCustomer(customer.get()));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }



    @Override
    public ResponseEntity<ApiResponse<List<Banking>>> getAllBanking() {
        try {
            return ApiResponse.success("Banking retrieved successfully", HttpStatus.OK, bankingRepository.findAll());
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
