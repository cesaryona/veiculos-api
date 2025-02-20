package br.com.veiculos.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class StandardError {

    private String message;
    private Integer code;
    private LocalDateTime dateTime;

}