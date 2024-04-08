package com.rushabh.graphql.employeemanagement.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;

public class EmployeeManagementException extends Exception implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String message;
    private final HttpStatus httpStatus;

    public EmployeeManagementException(String message) {
        super(message);
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public EmployeeManagementException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public EmployeeManagementException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    }

    public EmployeeManagementException(Throwable cause) {
        super(cause);
        this.message = "";
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
