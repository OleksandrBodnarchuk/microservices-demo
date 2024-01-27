package pl.alex.employeeservice.service;

import java.util.UUID;
import pl.alex.employeeservice.dto.EmployeeDTO;


public interface EmployeeService {
  void saveEmployee(EmployeeDTO employeeDTO);

  EmployeeDTO getByUUID(UUID uuid);
}
