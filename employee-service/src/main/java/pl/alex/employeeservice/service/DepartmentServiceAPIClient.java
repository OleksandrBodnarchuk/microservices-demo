package pl.alex.employeeservice.service;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alex.employeeservice.dto.DepartmentDTO;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface DepartmentServiceAPIClient {

  @GetMapping("/api/departments")
  DepartmentDTO getDepartmentByDepartmentUUID(@RequestParam(value = "id") UUID departmentUUID);

}
