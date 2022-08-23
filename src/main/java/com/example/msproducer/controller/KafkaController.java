package com.example.msproducer.controller;

import com.example.msproducer.dto.PaymentDto;
import com.example.msproducer.dto.PurchaseDto;
import com.example.msproducer.dto.TransactionDto;
import com.example.msproducer.enums.PaymentModeEnum;
import com.example.msproducer.producer.PaymentTransactionProducer;
import com.example.msproducer.producer.PaymentWalletProducer;
import com.example.msproducer.producer.PurchaseProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/kafka")
public class KafkaController {
  private final PaymentTransactionProducer paymentTransactionProducer;
  private final PaymentWalletProducer paymentWalletProducer;

  private final PurchaseProducer purchaseProducer;

  @PostMapping("/sendPayment")
  public ResponseEntity<String> sendMessage(@RequestBody PaymentDto paymentDto) {
    log.info("Sending message {}", paymentDto);
    paymentTransactionProducer.sendMessage(paymentDto);
    paymentWalletProducer.sendMessage(paymentDto);
    return ResponseEntity.ok("Message sent");
  }

  @PostMapping("/acceptPurchase")
  public ResponseEntity<String> sendMessage(@RequestBody PurchaseDto purchaseDto) {
    log.info("Sending message {}", purchaseDto);
    if(purchaseDto.getPaymentOriginMode().equals(PaymentModeEnum.YANKI.getValue())
        && purchaseDto.getPaymentDestinyMode().equals(PaymentModeEnum.YANKI.getValue())){

      //Send PaymentDto to pay through yanki
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setAmount(purchaseDto.getAmountSoles());
      paymentDto.setPhoneNumberOrigin(purchaseDto.getPaymentOriginNumber());
      paymentDto.setPhoneNumberDestination(purchaseDto.getPaymentDestinyNumber());
      paymentDto.setDateTime(purchaseDto.getDateTime());
      paymentTransactionProducer.sendMessage(paymentDto);
      paymentWalletProducer.sendMessage(paymentDto);

      //Send TransactionDto to pay through bootcoins
      TransactionDto transactionDto = new TransactionDto();
      transactionDto.setPaymentDestinyMode(purchaseDto.getPaymentDestinyMode());
      transactionDto.setPaymentDestinyNumber(purchaseDto.getPaymentDestinyNumber());
      transactionDto.setSellerPhone(purchaseDto.getSellerPhone());
      transactionDto.setPurchaseTempId(purchaseDto.getPurchaseTempId());
      transactionDto.setDateTime(purchaseDto.getDateTime());
      purchaseProducer.sendMessage(purchaseDto);
    }
    return ResponseEntity.ok("Message sent");
  }
}
