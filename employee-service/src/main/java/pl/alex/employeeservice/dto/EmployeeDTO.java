package pl.alex.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Schema(
    name = "EmployeeDTO",
    description = "Employee Model information"
)
@Builder
public record EmployeeDTO(@Schema(description = "Employee name") String firstName,
                          @Schema(description = "Employee surname") String lastName,
                          @Schema(description = "Employee email") String email,
                          @Schema(description = "Employee department") @NotNull String departmentCode,
                          @Schema(description = "Employee organization") @NotNull String organizationCode) {

}
