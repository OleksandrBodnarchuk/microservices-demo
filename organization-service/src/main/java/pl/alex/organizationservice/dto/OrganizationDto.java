package pl.alex.organizationservice.dto;

import lombok.Builder;

@Builder
public record OrganizationDto(String name,
                              String description,
                              String code) {

}
