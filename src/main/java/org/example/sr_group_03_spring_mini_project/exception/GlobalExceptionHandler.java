package org.example.sr_group_03_spring_mini_project.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.example.sr_group_03_spring_mini_project.exception.auth.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    // Handle @RequestParam / @PathVariable validation (Spring Boot < 3.2)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            fieldName = fieldName.contains(".") ? fieldName.substring(fieldName.lastIndexOf(".") + 1) : fieldName;
            errors.put(fieldName, violation.getMessage());
        });

        return buildErrorResponse(errors, request);
    }



    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.NOT_FOUND, "User Not Found", ex.getMessage(), request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyExists(UserAlreadyExistsException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.CONFLICT, "User Already Exists", ex.getMessage(), request);
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    public ResponseEntity<ProblemDetail> handleEmailNotVerified(EmailNotVerifiedException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.FORBIDDEN, "Email Not Verified", ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ProblemDetail> handleInvalidCredentials(InvalidCredentialsException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.UNAUTHORIZED, "Invalid Credentials", ex.getMessage(), request);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<ProblemDetail> handleInvalidOtp(InvalidOtpException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.UNPROCESSABLE_CONTENT, "Invalid OTP", ex.getMessage(), request);
    }

    @ExceptionHandler(UserAlreadyVerifiedException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyVerified(UserAlreadyVerifiedException ex, HttpServletRequest request) {
        return buildProblemDetail(HttpStatus.CONFLICT, "Already Verified", ex.getMessage(), request);
    }

    private ResponseEntity<ProblemDetail> buildProblemDetail(HttpStatus status, String title, String detail, HttpServletRequest request) {
        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle(title);
        pd.setDetail(detail);
        pd.setProperty("instance", request.getRequestURI());
        pd.setProperty("timestamp", LocalDateTime.now());
        return ResponseEntity.status(status).body(pd);
    }

    //when provide error and  give path of error with error message
    private ResponseEntity<ProblemDetail> buildErrorResponse(
            Map<String, String> errors,
            HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("status", "ERROR");
        problemDetail.setProperty("errors", errors);
        problemDetail.setProperty("instance", request.getRequestURI());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }


    //handle with not found 404 , make new class with ApiException extents by RuntimeException.
    //HttpServletRequest for get URI


    //on api have some param (String detail, String title, String type, HttpStatus status)
    //so ex it have getter for provide to ProblemDetail
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ProblemDetail> handleApiException(
            ApiException ex,
            HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(ex.getStatus());
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setTitle(ex.getTitle());
        problemDetail.setType(URI.create(ex.getType()));
        problemDetail.setProperty("instance", request.getRequestURI());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }


    //invalid validate

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return buildErrorResponse(errors, request);
    }

    //invalid json

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemDetail> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail("Invalid JSON in request body. Please check syntax.");
        problemDetail.setTitle("Bad Request");
        problemDetail.setType(URI.create("http://localhost:8080/errors/malformed-json"));
        problemDetail.setProperty("instance", request.getRequestURI());
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

}
