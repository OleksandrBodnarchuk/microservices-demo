package pl.alex.employeeservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record EmployeeDTO(String firstName,
                          String lastName,
                          String email,
                          @NotNull String departmentCode) {

}
