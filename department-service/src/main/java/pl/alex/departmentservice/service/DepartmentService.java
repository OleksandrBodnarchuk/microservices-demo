package pl.alex.departmentservice.service;

import java.util.UUID;
import pl.alex.departmentservice.dto.DepartmentDTO;


public interface DepartmentService {
  void saveDepartment(DepartmentDTO departmentDTO);

  DepartmentDTO getByDepartmentUUID(UUID departmentUUID);
}
