package pl.alex.organizationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(
    name = "Organization Controller",
    description = "Organization controller returns everything about organization"
)
@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {

  private final OrganizationService organizationService;

  @Operation(
      summary = "Save Organization REST API",
      description = "Save Organization REST API - to save organization"
  )
  @ApiResponse(
      responseCode = "201",
      description = "Returns HTTP status 201 CREATED if everything is ok"
  )
  @PostMapping(value = "/save",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<OrganizationDto> saveOrganization(
      @RequestBody @Valid OrganizationDto organizationDto) {
    organizationService.saveOrganization(organizationDto);
    return new ResponseEntity<>(organizationDto, HttpStatus.CREATED);
  }

  @Operation(
      summary = "GET Organization REST API",
      description = "GET Organization REST API - to get organization by its UUID from database"
  )
  @ApiResponse(
      responseCode = "200",
      description = "Returns HTTP status 200 OK and OrganizationDto object"
  )
  @GetMapping("/{code}")
  public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String code) {
    return ResponseEntity.ok(organizationService.getByOrganizationCode(code));
  }

}
