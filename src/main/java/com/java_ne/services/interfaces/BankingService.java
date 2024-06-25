package com.java_ne.services.interfaces;

import com.java_ne.dtos.banking.CreateUpdateBanking;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.models.Banking;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BankingService {
    public ResponseEntity<ApiResponse<Banking>> createBanking(CreateUpdateBanking banking);
    public ResponseEntity<ApiResponse<Banking>> updateBanking(CreateUpdateBanking banking , Long bankingId);
    public ResponseEntity<ApiResponse<Banking>> deleteBanking(Long bankingId);
    public ResponseEntity<ApiResponse<Banking>> getBanking(Long bankingId);
    public ResponseEntity<ApiResponse<List<Banking>>> getBankingByCustomer(Long customerId);
    public ResponseEntity<ApiResponse<List<Banking>>> getAllBanking();
}
