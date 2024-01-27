package pl.alex.employeeservice.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.alex.employeeservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
