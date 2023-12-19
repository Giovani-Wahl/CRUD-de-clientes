package com.giovaniwahl.CRUDdeclientes.controllers.handlers;

import com.giovaniwahl.CRUDdeclientes.dtos.CustomError;
import com.giovaniwahl.CRUDdeclientes.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomError customError= new CustomError(Instant.now(),httpStatus.value(),e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(customError);
    }
}
