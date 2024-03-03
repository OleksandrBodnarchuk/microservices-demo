package pl.alex.organizationservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alex.organizationservice.dto.OrganizationDto;
import pl.alex.organizationservice.service.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

  private final OrganizationService organizationService;

  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<OrganizationDto> saveOrganization(
      @RequestBody @Valid OrganizationDto organizationDto) {
    organizationService.saveOrganization(organizationDto);
    return new ResponseEntity<>(organizationDto, HttpStatus.CREATED);
  }

  @GetMapping("/{code}")
  public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String code) {
    return ResponseEntity.ok(organizationService.getByOrganizationCode(code));
  }

}
