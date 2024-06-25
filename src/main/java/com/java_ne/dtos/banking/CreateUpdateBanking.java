package com.java_ne.dtos.banking;

import com.java_ne.enumerations.banking.EBankingType;
import com.java_ne.models.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUpdateBanking {
    private Customer customer;
    private String account;
    private Double amount;
    private EBankingType type;
}