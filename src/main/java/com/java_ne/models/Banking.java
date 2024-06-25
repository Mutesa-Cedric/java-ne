package com.java_ne.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.java_ne.enumerations.banking.EBankingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Banking extends Base {

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @Column(nullable = false)
    private Customer customer;
    @Column(nullable = false)
    private String account;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EBankingType type;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "banking_date_time")
    private LocalDateTime bankingDateTime;
}
