package com.example.proyecto_integrador_final.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler({ResourceNotFoundExeption.class})
    public ResponseEntity<String> tratamientoResourceNotFoundException(ResourceNotFoundExeption rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("mensaje : "+rnfe.getMessage());

    }
}
