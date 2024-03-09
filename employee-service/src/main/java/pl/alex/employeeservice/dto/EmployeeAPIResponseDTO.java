package pl.alex.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(
    name = "EmployeeAPIResponseDTO",
    description = "EmployeeAPIResponseDTO Model information that includes full Employee information"
)
@Builder
public record EmployeeAPIResponseDTO(EmployeeDTO employee,
                                     DepartmentDTO department,
                                     OrganizationDTO organization) {

}
