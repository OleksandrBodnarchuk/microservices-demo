package pl.alex.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.alex.employeeservice.dto.OrganizationDTO;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationServiceAPIClient {

  @GetMapping("/api/organizations/{code}")
  OrganizationDTO getOrganizationByCode(@PathVariable String code);

}
