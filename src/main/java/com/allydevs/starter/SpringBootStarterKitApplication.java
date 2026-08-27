package com.allydevs.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBootStarterKitApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootStarterKitApplication.class, args);
  }
}
