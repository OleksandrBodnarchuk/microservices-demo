package pl.alex.employeeservice.dto;

import lombok.Builder;

@Builder
public record OrganizationDTO(String name,
                              String description,
                              String code) {

}
