package com.java_ne.dtos.message;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUpdateMessage {
    private Long customerId;
    private String message;
}
