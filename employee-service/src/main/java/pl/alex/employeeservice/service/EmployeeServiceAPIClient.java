package pl.alex.employeeservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.alex.employeeservice.dto.DepartmentDTO;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface EmployeeServiceAPIClient {

  @GetMapping("/departments")
  DepartmentDTO getDepartmentByDepartmentCode(@RequestParam(value = "id") String departmentCode);

}
