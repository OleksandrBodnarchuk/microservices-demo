package pl.alex.departmentservice.service;

import pl.alex.departmentservice.dto.DepartmentDTO;


public interface DepartmentService {
  void saveDepartment(DepartmentDTO departmentDTO);

  DepartmentDTO getByDepartmentCode(String departmentCode);
}
