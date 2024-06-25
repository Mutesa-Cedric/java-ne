package com.java_ne.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateCustomer {
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;

    private LocalDate dob;
    private String account;
    private Double balance;
}
