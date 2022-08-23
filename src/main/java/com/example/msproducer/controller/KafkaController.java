package com.example.msproducer.controller;

import com.example.msproducer.dto.PaymentDto;
import com.example.msproducer.dto.PurchaseDto;
import com.example.msproducer.enums.PaymentTypeEnum;
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
    if(purchaseDto.getPaymentOriginType().equals(PaymentTypeEnum.YANKI.getValue())
        && purchaseDto.getPaymentOriginType().equals(PaymentTypeEnum.YANKI.getValue())){
      PaymentDto paymentDto = new PaymentDto();
      paymentDto.setAmount(purchaseDto.getAmountSoles());
      paymentDto.setPhoneNumberOrigin(purchaseDto.getPaymentOriginNumber());
      paymentDto.setPhoneNumberDestination(purchaseDto.getPaymentDestinyNumber());
      paymentTransactionProducer.sendMessage(paymentDto);
      paymentWalletProducer.sendMessage(paymentDto);
      purchaseProducer.sendMessage(purchaseDto);
    }
    return ResponseEntity.ok("Message sent");
  }
}
