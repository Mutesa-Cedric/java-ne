package com.java_ne.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer  extends  Base{

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false, unique = true)
    private String mobile;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dob;
    @Column(nullable = false, unique = true)
    private String account;
    @Column(nullable = false)
    @Min(1)
    @Positive
    private Double balance;
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "last_update_date_time")
    private LocalDateTime lastUpdateDateTime;

}
