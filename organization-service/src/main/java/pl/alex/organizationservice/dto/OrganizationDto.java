package pl.alex.organizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Schema(
    name = "OrganizationDto",
    description = "OrganizationDto Model information"
)
@Builder
public record OrganizationDto(@Schema(description = "Department name") String name,
                              @Schema(description = "Department description") String description,
                              @Schema(description = "Department code") @NotBlank String code) {

}

