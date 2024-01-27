package pl.alex.employeeservice.dto;

import lombok.Builder;

@Builder
public record DepartmentDTO(String name,
                            String description,
                            String code) {

}
