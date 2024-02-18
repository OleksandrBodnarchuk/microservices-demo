package pl.alex.departmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record DepartmentDTO(String name,
                            String description,
                            @NotBlank String code) {

}
