package pl.alex.departmentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Schema(
    name = "DepartmentDTO",
    description = "Department Model information"
)
@Builder
public record DepartmentDTO(@Schema(description = "Department name") String name,
                            @Schema(description = "Department description") String description,
                            @Schema(description = "Department code") @NotBlank String code) {

}
