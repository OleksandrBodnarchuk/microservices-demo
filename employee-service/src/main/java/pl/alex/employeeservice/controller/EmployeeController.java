package pl.alex.employeeservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
    name = "Employee Controller",
    description = "Employee controller is one of the main controllers that communicates with other services"
)
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @Operation(
      summary = "Save Employee REST API",
      description = "Save Employee REST API - to save employee"
  )
  @ApiResponse(
      responseCode = "201",
      description = "Returns HTTP status 201 CREATED if everything is ok"
  )
  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO EmployeeDTO) {
    employeeService.saveEmployee(EmployeeDTO);
    return new ResponseEntity<>(EmployeeDTO, HttpStatus.CREATED);
  }

  @Operation(
      summary = "GET Employee REST API",
      description = "GET Employee REST API - to get employee by its UUID from database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "Returns HTTP status 200 OK and EmployeeAPIResponseDTO object"
  )
  @GetMapping
  public ResponseEntity<EmployeeAPIResponseDTO> getEmployeeByUUID(
      @RequestParam(value = "id") UUID uuid) {
    return ResponseEntity.ok(employeeService.getByUUID(uuid));
  }

}
