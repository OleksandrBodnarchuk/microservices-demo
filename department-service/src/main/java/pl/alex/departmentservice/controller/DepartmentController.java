package pl.alex.departmentservice.controller;

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
import pl.alex.departmentservice.dto.DepartmentDTO;
import pl.alex.departmentservice.service.DepartmentService;

@Tag(
    name = "Department Controller",
    description = "Test description..."
)
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentService departmentService;

  @Operation(
      summary = "Save Department REST API",
      description = "Save Department REST API - to save department"
  )
  @ApiResponse(
      responseCode = "201",
      description = "Returns HTTP status 201 CREATED if everything is ok"
  )
  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody @Valid DepartmentDTO departmentDTO) {
    departmentService.saveDepartment(departmentDTO);
    return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
  }

  @Operation(
      summary = "GET Department REST API",
      description = "GET Department REST API - to get department by its UUID from database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "Returns HTTP status 200 OK and DepartmentDTO object"
  )
  @GetMapping
  public ResponseEntity<DepartmentDTO> getDepartmentByDepartmentUUID(
      @RequestParam(value = "id") UUID departmentUUID) {
    return ResponseEntity.ok(departmentService.getByDepartmentUUID(departmentUUID));
  }

}
