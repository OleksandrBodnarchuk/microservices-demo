package pl.alex.departmentservice.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {

}
