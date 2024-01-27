package pl.alex.employeeservice.exception;

import java.util.Date;

public record ErrorDetails(Date timestamp,
                           String message,
                           String details) {

}
