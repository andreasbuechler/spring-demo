package com.buechler.spring.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@ComponentScan
@RestController
public class App {

  @Autowired
  private Person person;

  public static void main(String[] args) {

    SpringApplication.run(App.class, args);

  }

  @RequestMapping("/person")
  public String printPerson() {
    return "Hello! I'm " + person.getFirstname() + " " + person.getLastname() + ". My address is "
        + person.getAddress().getStreet();
  }

}
