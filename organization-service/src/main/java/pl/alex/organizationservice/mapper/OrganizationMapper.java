package pl.alex.organizationservice.mapper;

import pl.alex.organizationservice.dto.OrganizationDto;
import pl.alex.organizationservice.entity.Organization;

public class OrganizationMapper {

  public static OrganizationDto toDto(Organization organization) {
    return OrganizationDto.builder()
        .name(organization.getName())
        .description(organization.getDescription())
        .code(organization.getCode())
        .build();
  }

  public static Organization toEntity(OrganizationDto organizationDto) {
    return Organization.builder()
        .name(organizationDto.name())
        .code(organizationDto.code())
        .description(organizationDto.description())
        .build();
  }


}
