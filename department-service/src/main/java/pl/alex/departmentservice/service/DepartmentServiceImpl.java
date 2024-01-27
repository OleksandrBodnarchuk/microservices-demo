package pl.alex.departmentservice.service;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.alex.departmentservice.exception.DepartmentNotFoundException;
import pl.alex.departmentservice.dto.DepartmentDTO;
import pl.alex.departmentservice.entity.Department;
import pl.alex.departmentservice.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public void saveDepartment(DepartmentDTO departmentDTO) {
    Department department = Department.builder()
        .name(departmentDTO.name())
        .code(departmentDTO.code())
        .description(departmentDTO.description())
        .build();
    departmentRepository.save(department);
  }

  @Override
  public DepartmentDTO getByDepartmentCode(String departmentCode) {
    Optional<Department> optionalDepartment = departmentRepository.findByCode(departmentCode);
    if (optionalDepartment.isPresent()) {
      Department department = optionalDepartment.get();
      return DepartmentDTO.builder()
          .name(department.getName())
          .description(department.getDescription())
          .code(department.getCode())
          .build();
    }
    throw new DepartmentNotFoundException(String.format("Department not found: %s", departmentCode));
  }
}
