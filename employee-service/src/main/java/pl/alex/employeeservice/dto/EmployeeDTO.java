package pl.alex.employeeservice.dto;

import lombok.Builder;
import pl.alex.employeeservice.entity.Employee;

@Builder
public record EmployeeDTO(String firstName,
                          String lastName,
                          String email,
                          String departmentCode) {

  public static EmployeeDTO getEmployeeDTO(Employee employee) {
    return EmployeeDTO.builder()
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .email(employee.getEmail())
        .departmentCode(employee.getDepartmentCode())
        .build();
  }

}
