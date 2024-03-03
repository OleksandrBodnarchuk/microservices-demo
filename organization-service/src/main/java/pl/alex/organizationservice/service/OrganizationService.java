package pl.alex.organizationservice.service;

import java.util.UUID;
import pl.alex.organizationservice.dto.OrganizationDto;

public interface OrganizationService {

  OrganizationDto getByOrganizationUUID(UUID organizationUUID);

  void saveOrganization(OrganizationDto organizationDto);

  OrganizationDto getByOrganizationCode(String organizationCode);
}
