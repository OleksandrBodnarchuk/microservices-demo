package pl.alex.employeeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import pl.alex.employeeservice.dto.DepartmentDTO;
import pl.alex.employeeservice.dto.EmployeeAPIResponseDTO;
import pl.alex.employeeservice.dto.EmployeeDTO;
import pl.alex.employeeservice.dto.OrganizationDTO;
import pl.alex.employeeservice.entity.Employee;
import pl.alex.employeeservice.exception.DepartmentServiceException;
import pl.alex.employeeservice.exception.EmployeeNotFoundException;
import pl.alex.employeeservice.exception.UnexpectedServiceException;
import pl.alex.employeeservice.mapper.EmployeeMapper;
import pl.alex.employeeservice.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, CallForDepartmentDetails {

  private final EmployeeRepository employeeRepository;
  private final DepartmentServiceAPIClient departmentServiceAPIClient;
  private final OrganizationServiceAPIClient organizationServiceAPIClient;
  private final ObjectMapper objectMapper;

  @Override
  public void saveEmployee(EmployeeDTO employeeDTO) {
    employeeRepository.save(EmployeeMapper.toEntity(employeeDTO));
  }

  @Override
  public EmployeeAPIResponseDTO getByUUID(UUID uuid) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
    if (optionalEmployee.isPresent()) {
      EmployeeDTO employeeDTO = EmployeeMapper.toDto(optionalEmployee.get());
      return EmployeeAPIResponseDTO.builder()
          .employee(employeeDTO)
          .department(getByDepartmentUUID(employeeDTO.departmentCode()))
          .organization(getByOrganizationCode(employeeDTO.organizationCode()))
          .build();
    }
    throw new EmployeeNotFoundException(String.format("Employee %s not found", uuid));
  }

  @Override
  public OrganizationDTO getByOrganizationCode(String organizationCode) {
    try {
      return organizationServiceAPIClient.getOrganizationByCode(organizationCode);
    } catch (Exception e) {
      throw new DepartmentServiceException(e.getMessage());
    }
  }

  @Override
  public DepartmentDTO getByDepartmentUUID(String departmentUUID) {
    try {
      return departmentServiceAPIClient.getDepartmentByDepartmentUUID(UUID.fromString(departmentUUID));
    } catch (Exception e) {
      throw new DepartmentServiceException(e.getMessage());
    }

  }

  @Override
  public URI prepareUrl(UrlParameters parameters) {
    try {
      return UriComponentsBuilder.newInstance()
          .uri(new URI(parameters.getUrl()))
          .queryParam("id", parameters.getId())
          .build()
          .toUri();
    } catch (Exception e) {
      throw new UnexpectedServiceException(e.getMessage());
    }
  }

  @Override
  public ObjectMapper getObjectMapper() {
    return this.objectMapper;
  }
}
