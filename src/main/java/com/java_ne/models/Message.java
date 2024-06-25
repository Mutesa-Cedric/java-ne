package com.java_ne.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message extends Base {

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @Column(nullable = false)
    private Customer customer;
    @Column(nullable = false)
    private String message;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "date_time")
    private LocalDateTime dateTime;

}
