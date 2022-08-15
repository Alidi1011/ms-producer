package com.example.msproducer.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {
  @KafkaListener(topics = "${kafka.topic.name}")
  public void listener(String message) {
    log.debug("Message received {} ", message);
    //Do something
    log.info("Mensaje recibido por el consumidor");
  }
}
