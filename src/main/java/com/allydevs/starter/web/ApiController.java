package com.allydevs.starter.web;

import com.allydevs.starter.config.ApplicationProperties;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("/echo")
  public EchoResponse echo(@Valid @RequestBody EchoRequest request) {
    return new EchoResponse(request.message());
  }
}
