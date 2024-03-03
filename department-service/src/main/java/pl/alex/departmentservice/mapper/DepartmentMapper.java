package pl.alex.departmentservice.mapper;

import pl.alex.departmentservice.dto.DepartmentDTO;
import pl.alex.departmentservice.entity.Department;

public class DepartmentMapper {

  public static DepartmentDTO toDto(Department department) {
    return DepartmentDTO.builder()
        .name(department.getName())
        .description(department.getDescription())
        .code(department.getCode())
        .build();
  }

  public static Department toEntity(DepartmentDTO departmentDTO) {
    return Department.builder()
        .name(departmentDTO.name())
        .code(departmentDTO.code())
        .description(departmentDTO.description())
        .build();
  }


}
