package pl.alex.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Schema(name = "OrganizationDTO", description = "Organization Model information")
@Builder
public record OrganizationDTO(@Schema(description = "Organization name") String name,
                              @Schema(description = "Organization description") String description,
                              @Schema(description = "Organization code") @NotBlank String code) {

}

