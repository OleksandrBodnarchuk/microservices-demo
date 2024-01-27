package pl.alex.employeeservice.dto;

import lombok.Builder;

@Builder
public record EmployeeAPIResponseDTO(EmployeeDTO employee,
                                     DepartmentDTO department) {

}
