package pl.alex.organizationservice.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.alex.organizationservice.dto.OrganizationDto;
import pl.alex.organizationservice.entity.Organization;
import pl.alex.organizationservice.mapper.OrganizationMapper;
import pl.alex.organizationservice.repository.OrganizationRepository;
import pl.alex.organizationservice.exception.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

  private static final String ORGANIZATION_NOT_FOUND = "Organization not found: %s";
  public static final String ORGANIZATION_ALREADY_EXISTS = "Organization already exists: %s";
  private final OrganizationRepository organizationRepository;

  @Override
  public OrganizationDto getByOrganizationUUID(UUID organizationUUID) {
    Optional<Organization> optionalDepartment = organizationRepository.findById(organizationUUID);
    if (optionalDepartment.isPresent()) {
      return OrganizationMapper.toDto(optionalDepartment.get());
    } else {
      throw new OrganizationNotFoundException(
          String.format(ORGANIZATION_NOT_FOUND, organizationUUID));
    }
  }

  @Override
  public void saveOrganization(OrganizationDto organizationDto) {
    if (organizationRepository.findByCode(organizationDto.code()).isEmpty()) {
      organizationRepository.save(OrganizationMapper.toEntity(organizationDto));
      log.info("[saveOrganization] - OK");
    } else {
      throw new OrganizationNotFoundException(
          String.format(ORGANIZATION_ALREADY_EXISTS, organizationDto.code()));
    }
  }

  @Override
  public OrganizationDto getByOrganizationCode(String organizationCode) {
    return OrganizationMapper.toDto(organizationRepository
        .findByCode(organizationCode)
        .orElseThrow(
            () -> new OrganizationNotFoundException(
                String.format(ORGANIZATION_NOT_FOUND, organizationCode)))
    );
  }
}
