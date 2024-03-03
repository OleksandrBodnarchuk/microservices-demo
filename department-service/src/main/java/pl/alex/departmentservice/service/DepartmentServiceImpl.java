package pl.alex.departmentservice.service;

import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.alex.departmentservice.dto.DepartmentDTO;
import pl.alex.departmentservice.entity.Department;
import pl.alex.departmentservice.exception.DepartmentNotFoundException;
import pl.alex.departmentservice.mapper.DepartmentMapper;
import pl.alex.departmentservice.repository.DepartmentRepository;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

  private final DepartmentRepository departmentRepository;

  @Override
  public void saveDepartment(DepartmentDTO departmentDTO) {
    departmentRepository.save(DepartmentMapper.toEntity(departmentDTO));
  }

  @Override
  public DepartmentDTO getByDepartmentUUID(UUID departmentUUID) {
    Optional<Department> optionalDepartment = departmentRepository.findById(departmentUUID);
    if (optionalDepartment.isPresent()) {
      return DepartmentMapper.toDto(optionalDepartment.get());
    }
    throw new DepartmentNotFoundException(
        String.format("Department not found: %s", departmentUUID));
  }
}
