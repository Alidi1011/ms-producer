package com.example.msproducer.controller;

import com.example.msproducer.dto.PaymentDto;
import com.example.msproducer.producer.KafkaProducer;
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
  private final KafkaProducer kafkaProducer;

  @PostMapping("/sendPayment")
  public ResponseEntity<String> sendMessage(@RequestBody PaymentDto paymentDto) {
    log.info("Sending message {}", paymentDto);
    kafkaProducer.sendMessage(paymentDto);
    return ResponseEntity.ok("Message sent");
  }
}
