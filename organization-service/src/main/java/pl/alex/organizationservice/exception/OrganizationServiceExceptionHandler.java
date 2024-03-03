package pl.alex.organizationservice.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class OrganizationServiceExceptionHandler {

  @ExceptionHandler({OrganizationNotFoundException.class})
  public ResponseEntity<?> organizationNotFoundException(OrganizationNotFoundException ex,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({OrganizationAlreadyExistsException.class})
  public ResponseEntity<?> organizationAlreadyExistsException(OrganizationAlreadyExistsException ex,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
        request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
  }
}