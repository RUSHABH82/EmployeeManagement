package com.rushabh.graphql.employeemanagement.exception;

import com.rushabh.graphql.employeemanagement.domain.ResultStatus;
import com.rushabh.graphql.employeemanagement.domain.ResultStatusResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Class ExceptionControllerAdvice.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    public ExceptionControllerAdvice() {
        super();
    }

    /**
     * Handle Generic exception.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResultStatusResponse> handleException(Exception exception) {
        ResultStatus status = new ResultStatus();
        status.setStatus("FAILED");
        status.setErrorCode("Technical Difficulty!");
        status.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(new ResultStatusResponse(status), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle EmployeeManagementException exception.
     *
     * @param employeeManagementException the EmployeeManagementException
     * @return the response entity
     */
    @ExceptionHandler(EmployeeManagementException.class)
    public ResponseEntity<ResultStatusResponse> handleRestException(EmployeeManagementException employeeManagementException) {
        ResultStatus status = new ResultStatus();
        status.setStatus("FAILED");
        status.setErrorCode(Integer.toString(employeeManagementException.getHttpStatus().value()));
        status.setErrorMessage(employeeManagementException.getMessage());
        return new ResponseEntity<>(new ResultStatusResponse(status), employeeManagementException.getHttpStatus());
    }
}
