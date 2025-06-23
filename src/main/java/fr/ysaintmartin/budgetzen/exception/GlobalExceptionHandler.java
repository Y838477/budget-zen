package fr.ysaintmartin.budgetzen.exception;

import fr.ysaintmartin.budgetzen.journal.InvalidTransactionJournalRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

        return ResponseEntity.badRequest()
                .body(ex.getBindingResult().getFieldErrors().stream()
                        .findFirst()
                        .map(fieldError -> new InvalidTransactionJournalRequest(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), String.format(fieldError.getDefaultMessage(), fieldError.getRejectedValue(), fieldError.getField())))
                        .orElse(new InvalidTransactionJournalRequest(Instant.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "La requête envoyée est incorrecte.")));
    }
}
