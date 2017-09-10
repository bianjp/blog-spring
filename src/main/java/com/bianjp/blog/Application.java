package com.bianjp.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

  public static void main(String[] args) {
    // Use UTC time everywhere
    System.setProperty("user.timezone", "UTC");
    TimeZone.setDefault(null);
    SpringApplication.run(Application.class, args);
  }
}
