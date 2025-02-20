package br.com.veiculos.api.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarcaEnum {

    FIAT("Fiat"),
    VOLKSWAGEN("Volkswagen"),
    TOYOTA("Toyota"),
    FORD("Ford"),
    HONDA("Honda"),
    BMW("BMW"),
    HYUNDAI("Hyundai");

    private final String marca;

}
