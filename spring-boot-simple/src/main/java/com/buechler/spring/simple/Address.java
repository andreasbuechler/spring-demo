package com.buechler.spring.simple;

import org.springframework.stereotype.Component;

@Component
public class Address {

  private String street;

  public Address() {
    this.street = "Infinite Loop 1";
  }

  public String getStreet() {
    return street;
  }

}
