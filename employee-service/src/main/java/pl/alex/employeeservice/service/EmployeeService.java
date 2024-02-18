package pl.alex.employeeservice.service;

import java.util.UUID;
import pl.alex.employeeservice.dto.DepartmentDTO;
import pl.alex.employeeservice.dto.EmployeeAPIResponseDTO;
import pl.alex.employeeservice.dto.EmployeeDTO;


public interface EmployeeService {
  void saveEmployee(EmployeeDTO employeeDTO);

  EmployeeAPIResponseDTO getByUUID(UUID uuid);
  DepartmentDTO getByDepartmentUUID(String departmentCode);
}
