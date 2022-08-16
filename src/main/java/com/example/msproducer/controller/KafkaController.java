package com.example.msproducer.controller;

import com.example.msproducer.dto.PaymentDto;
import com.example.msproducer.producer.PaymentTransactionProducer;
import com.example.msproducer.producer.PaymentWalletProducer;
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

  @PostMapping("/sendPayment")
  public ResponseEntity<String> sendMessage(@RequestBody PaymentDto paymentDto) {
    log.info("Sending message {}", paymentDto);
    paymentTransactionProducer.sendMessage(paymentDto);
    return ResponseEntity.ok("Message sent");
  }

  @PostMapping("/sendPaymentWallet")
  public ResponseEntity<String> sendMessageWallet(@RequestBody PaymentDto paymentDto) {
    log.info("Sending message {}", paymentDto);
    paymentWalletProducer.sendMessage(paymentDto);
    return ResponseEntity.ok("Message sent");
  }
}
