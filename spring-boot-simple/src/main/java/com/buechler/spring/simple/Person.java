package com.buechler.spring.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

  private String firstname, lastname;
  @Autowired
  private Address address;

  public Person() {
    this.firstname = "John";
    this.lastname = "Smith";
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public Address getAddress() {
    return address;
  }

}
