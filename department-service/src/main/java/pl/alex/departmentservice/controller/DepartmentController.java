package pl.alex.departmentservice.controller;

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

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

  private final DepartmentService departmentService;

  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
    departmentService.saveDepartment(departmentDTO);
    return new ResponseEntity<>(departmentDTO, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<DepartmentDTO> getDepartmentByUUID(@RequestParam(value = "id") String departmentCode) {
    return ResponseEntity.ok(departmentService.getByDepartmentCode(departmentCode));
  }

}
