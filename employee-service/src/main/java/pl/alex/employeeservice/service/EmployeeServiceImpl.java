package pl.alex.employeeservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.alex.employeeservice.dto.DepartmentDTO;
import pl.alex.employeeservice.dto.EmployeeAPIResponseDTO;
import pl.alex.employeeservice.dto.EmployeeDTO;
import pl.alex.employeeservice.entity.Employee;
import pl.alex.employeeservice.exception.DepartmentServiceException;
import pl.alex.employeeservice.exception.EmployeeNotFoundException;
import pl.alex.employeeservice.exception.UnexpectedServiceException;
import pl.alex.employeeservice.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, CallForDepartmentDetails {

  private final EmployeeRepository employeeRepository;
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Value("${department.service.url}")
  private String departmentServiceUrl;

  @Override
  public void saveEmployee(EmployeeDTO employeeDTO) {
    Employee employee = Employee.builder()
        .firstName(employeeDTO.firstName())
        .lastName(employeeDTO.lastName())
        .email(employeeDTO.email())
        .departmentCode(employeeDTO.departmentCode())
        .build();
    employeeRepository.save(employee);
  }

  @Override
  public EmployeeAPIResponseDTO getByUUID(UUID uuid) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
    if (optionalEmployee.isPresent()) {
      EmployeeDTO employeeDTO = EmployeeDTO.getEmployeeDTO(optionalEmployee.get());
      return EmployeeAPIResponseDTO.builder()
          .employee(employeeDTO)
          .department(getDepartmentByCode(employeeDTO.departmentCode()))
          .build();
    }
    throw new EmployeeNotFoundException(String.format("Employee %s not found", uuid));
  }

  @Override
  public DepartmentDTO getDepartmentByCode(String departmentCode) {
    final URI uri = prepareUrl(UrlParameters.builder()
        .url(departmentServiceUrl)
        .id(departmentCode)
        .build());
    final ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
        null, String.class);

    final String jsonResponse = responseEntity.getBody();
    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      return getValueFromJson(jsonResponse, DepartmentDTO.class);
    }
    throw new DepartmentServiceException(jsonResponse);
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
