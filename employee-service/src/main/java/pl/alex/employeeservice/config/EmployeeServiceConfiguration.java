package pl.alex.employeeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class EmployeeServiceConfiguration {

  @Bean
  public WebClient webClient() {
    return WebClient.create();
  }
}
