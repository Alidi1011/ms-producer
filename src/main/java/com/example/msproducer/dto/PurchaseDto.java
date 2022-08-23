package com.example.msproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {
    private BigDecimal amountBootcoin;
    private BigDecimal amountSoles;
    private String paymentOriginType;
    private String paymentOriginNumber;
    private String paymentDestinyType;
    private String paymentDestinyNumber;
    private String dateTime;
    private String purchaseTempId;
}
