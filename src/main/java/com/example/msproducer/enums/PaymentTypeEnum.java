package com.example.msproducer.enums;

public enum PaymentTypeEnum {
  YANKI("YANKI"),
  TRANSFER("TRANSFER");
  private final String value;

  PaymentTypeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
