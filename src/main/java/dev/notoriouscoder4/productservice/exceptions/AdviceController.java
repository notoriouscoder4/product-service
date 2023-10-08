package dev.notoriouscoder4.productservice.exceptions;

import dev.notoriouscoder4.productservice.thirdparty.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(
                new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage()), HttpStatus.NOT_FOUND
        );
    }
}
