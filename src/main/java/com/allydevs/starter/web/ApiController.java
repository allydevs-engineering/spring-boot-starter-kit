package com.allydevs.starter.web;

import com.allydevs.starter.config.ApplicationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/v1")
public class ApiController {

  private final ApplicationProperties applicationProperties;

  public ApiController(ApplicationProperties applicationProperties) {
    this.applicationProperties = applicationProperties;
  }

  @GetMapping
  public ApiResponse getApplicationInfo() {
    return new ApiResponse(applicationProperties.name(), applicationProperties.version(), "UP");
  }
}
