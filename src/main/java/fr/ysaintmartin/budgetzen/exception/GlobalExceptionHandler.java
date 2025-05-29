package fr.ysaintmartin.budgetzen.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest()
                .body(ex.getBindingResult().getFieldErrors().stream()
                        .map(fieldError -> String.format(fieldError.getDefaultMessage(), fieldError.getRejectedValue(), fieldError.getField()))
                        .collect(Collectors.joining("&&")));
    }
}
