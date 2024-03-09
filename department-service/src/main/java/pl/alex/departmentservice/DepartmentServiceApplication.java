package pl.alex.departmentservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
    info = @Info(
        title = "Department Service API",
        description = "REST API Documentation for Department Service",
        version = "v.1.0",
        contact = @Contact(
            name = "Alex",
            email = "dummyemail@mail.com",
            url = "dummy-url.pl"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "dummy-url.pl"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Department Service Doc",
        url = "dummy-url.pl"
    )
)
@SpringBootApplication
public class DepartmentServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(DepartmentServiceApplication.class, args);
  }

}
