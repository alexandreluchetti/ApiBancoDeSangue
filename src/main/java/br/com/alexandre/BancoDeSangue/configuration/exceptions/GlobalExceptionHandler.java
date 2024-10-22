package br.com.alexandre.BancoDeSangue.configuration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonException.class)
    public ResponseEntity<ExceptionEntity> personExceptionHandler(PersonException ex) {
        ExceptionEntity errorDetails = new ExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(FormatException.class)
    public ResponseEntity<ExceptionEntity> formatExceptionHandler(FormatException ex) {
        ExceptionEntity errorDetails = new ExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }

    @ExceptionHandler(EmptyListException.class)
    public ResponseEntity<ExceptionEntity> emptyListExceptionHandler(EmptyListException ex) {
        ExceptionEntity errorDetails = new ExceptionEntity(HttpStatus.NO_CONTENT.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionEntity> globalExceptionHandler(Exception ex) {
        ExceptionEntity errorDetails = new ExceptionEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDetails);
    }
}
