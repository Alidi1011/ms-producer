package com.example.msproducer.producer;

import com.example.msproducer.dto.PaymentDto;
import com.example.msproducer.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@RequiredArgsConstructor
@Slf4j
public class PurchaseProducer {

  private final KafkaTemplate<String, PurchaseDto> kafkaTemplate;

  @Value(value = "${kafka.topic3.name}")
  private String topic;

  public void sendMessage(PurchaseDto purchaseDto) {

    ListenableFuture<SendResult<String, PurchaseDto>> future = kafkaTemplate.send(this.topic, purchaseDto);

    future.addCallback(new ListenableFutureCallback<SendResult<String, PurchaseDto>>() {
      @Override
      public void onSuccess(SendResult<String, PurchaseDto> result) {
        log.info("Message {} has been sent ", purchaseDto);
      }
      @Override
      public void onFailure(Throwable ex) {
        log.error("Something went wrong with the puchaseDto {} ", purchaseDto);
      }
    });
  }
}
