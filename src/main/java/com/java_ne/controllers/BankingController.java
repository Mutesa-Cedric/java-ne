package com.java_ne.controllers;

import com.java_ne.dtos.banking.CreateUpdateBanking;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Banking;
import com.java_ne.services.interfaces.BankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/banking")
@RequiredArgsConstructor
public class BankingController {
    private final BankingService bankingService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Banking>> createBanking(@RequestBody CreateUpdateBanking banking) {
        return bankingService.createBanking(banking);
    }

    @PutMapping("/update/{bankingId}")
    public ResponseEntity<ApiResponse<Banking>> updateBanking(@RequestBody CreateUpdateBanking banking, @PathVariable Long bankingId) {
        return bankingService.updateBanking(banking, bankingId);
    }

    @DeleteMapping("/delete/{bankingId}")
    public ResponseEntity<ApiResponse<Banking>> deleteBanking(@PathVariable Long bankingId) {
        return bankingService.deleteBanking(bankingId);
    }

    @GetMapping("/id/{bankingId}")
    public ResponseEntity<ApiResponse<Banking>> getBanking(@PathVariable Long bankingId) {
        return bankingService.getBanking(bankingId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ApiResponse<List<Banking>>> getBankingByCustomer(@PathVariable Long customerId) {
        return bankingService.getBankingByCustomer(customerId);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<Banking>>> getAllBanking() {
        return bankingService.getAllBanking();
    }
}