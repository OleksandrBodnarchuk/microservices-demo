package pl.alex.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DepartmentServiceException extends RuntimeException {

  public DepartmentServiceException(String message) {
    super(message);
  }
}
