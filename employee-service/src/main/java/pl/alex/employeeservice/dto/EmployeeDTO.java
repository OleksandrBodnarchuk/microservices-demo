package pl.alex.employeeservice.dto;

import lombok.Builder;

@Builder()
public record EmployeeDTO(String firstName,
                          String lastName,
                          String email) {

}
