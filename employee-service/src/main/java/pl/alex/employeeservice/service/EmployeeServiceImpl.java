package pl.alex.employeeservice.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.alex.employeeservice.dto.EmployeeDTO;
import pl.alex.employeeservice.entity.Employee;
import pl.alex.employeeservice.exception.EmployeeNotFoundException;
import pl.alex.employeeservice.repository.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

  @Override
  public void saveEmployee(EmployeeDTO employeeDTO) {
    Employee employee = Employee.builder()
        .firstName(employeeDTO.firstName())
        .lastName(employeeDTO.lastName())
        .email(employeeDTO.email())
        .build();
    employeeRepository.save(employee);
  }

  @Override
  public EmployeeDTO getByUUID(UUID uuid) {
    Optional<Employee> optionalEmployee = employeeRepository.findById(uuid);
    if (optionalEmployee.isPresent()) {
      Employee employee = optionalEmployee.get();
      return EmployeeDTO.builder()
          .firstName(employee.getFirstName())
          .lastName(employee.getLastName())
          .email(employee.getEmail())
          .build();
    }
    throw new EmployeeNotFoundException(String.format("Employee %s not found", uuid));
  }
}
