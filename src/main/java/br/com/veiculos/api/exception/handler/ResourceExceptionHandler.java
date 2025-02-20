package br.com.veiculos.api.exception.handler;

import br.com.veiculos.api.exception.NotFoundException;
import br.com.veiculos.api.exception.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> handler(final NotFoundException exception) {
        var code = HttpStatus.NOT_FOUND;

        var error = StandardError.builder()
                .message(exception.getMessage())
                .code(code.value())
                .dateTime(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, code);
    }
}