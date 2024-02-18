package pl.alex.employeeservice.controller;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.employeeservice.dto.EmployeeAPIResponseDTO;
import pl.alex.employeeservice.dto.EmployeeDTO;
import pl.alex.employeeservice.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO EmployeeDTO) {
    employeeService.saveEmployee(EmployeeDTO);
    return new ResponseEntity<>(EmployeeDTO, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<EmployeeAPIResponseDTO> getEmployeeByUUID(@RequestParam(value = "id") UUID uuid) {
    return ResponseEntity.ok(employeeService.getByUUID(uuid));
  }

}
