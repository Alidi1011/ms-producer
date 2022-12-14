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
public class PurchaseDto {
    private BigDecimal amountBootcoin;
    private BigDecimal amountSoles;
    private String paymentOriginMode;
    private String paymentOriginNumber;
    private String paymentDestinyMode;
    private String paymentDestinyNumber;
    private String buyerPhone;
    private String sellerPhone;
    private String dateTime;
    private String purchaseTempId;
}
