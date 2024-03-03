package pl.alex.employeeservice.mapper;

import pl.alex.employeeservice.dto.EmployeeDTO;
import pl.alex.employeeservice.entity.Employee;

public class EmployeeMapper {

  public static Employee toEntity(EmployeeDTO employeeDTO) {
    return Employee.builder()
        .firstName(employeeDTO.firstName())
        .lastName(employeeDTO.lastName())
        .email(employeeDTO.email())
        .departmentCode(employeeDTO.departmentCode())
        .organizationCode(employeeDTO.organizationCode())
        .build();
  }

  public static EmployeeDTO toDto(Employee employee) {
    return EmployeeDTO.builder()
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .email(employee.getEmail())
        .departmentCode(employee.getDepartmentCode())
        .organizationCode(employee.getOrganizationCode())
        .build();
  }

}
