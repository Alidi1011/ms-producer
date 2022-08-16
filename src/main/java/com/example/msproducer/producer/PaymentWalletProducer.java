package com.example.msproducer.producer;

import com.example.msproducer.dto.PaymentDto;
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
public class PaymentWalletProducer {

  private final KafkaTemplate<String, PaymentDto> kafkaTemplate;

  @Value(value = "${kafka.topic2.name}")
  private String topic;

  public void sendMessage(PaymentDto paymentDto) {

    ListenableFuture<SendResult<String, PaymentDto>> future = kafkaTemplate.send(this.topic, paymentDto);

    future.addCallback(new ListenableFutureCallback<SendResult<String, PaymentDto>>() {
      @Override
      public void onSuccess(SendResult<String, PaymentDto> result) {
        log.info("Message with PaymentWalletProducer {} has been sent ", paymentDto);
      }
      @Override
      public void onFailure(Throwable ex) {
        log.error("Something went wrong with the paymentDto {} ", paymentDto);
      }
    });
  }
}
