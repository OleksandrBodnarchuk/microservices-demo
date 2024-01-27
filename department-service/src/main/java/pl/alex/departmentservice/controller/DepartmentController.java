package pl.alex.departmentservice.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
    return ResponseEntity.ok(departmentDTO);
  }

  @GetMapping
  public ResponseEntity<DepartmentDTO> getDepartmentByUUID(@RequestParam(value = "id") UUID uuid) {
    return ResponseEntity.ok(departmentService.getByUUID(uuid));
  }

}
