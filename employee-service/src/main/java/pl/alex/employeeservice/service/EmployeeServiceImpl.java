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
import pl.alex.employeeservice.entity.Employee;
import pl.alex.employeeservice.exception.DepartmentServiceException;
import pl.alex.employeeservice.exception.EmployeeNotFoundException;
import pl.alex.employeeservice.exception.UnexpectedServiceException;
import pl.alex.employeeservice.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, CallForDepartmentDetails {

  private final EmployeeRepository employeeRepository;
  private final EmployeeServiceAPIClient apiClient;
  private final ObjectMapper objectMapper;

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
          .department(getByDepartmentUUID(employeeDTO.departmentCode()))
          .build();
    }
    throw new EmployeeNotFoundException(String.format("Employee %s not found", uuid));
  }

  @Override
  public DepartmentDTO getByDepartmentUUID(String departmentUUID) {
    try {
      return apiClient.getDepartmentByDepartmentUUID(UUID.fromString(departmentUUID));
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
