package com.example.msproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private BigDecimal amount;
    private String phoneNumberOrigin;
    private String phoneNumberDestination;
    private LocalDateTime dateTime;
}
