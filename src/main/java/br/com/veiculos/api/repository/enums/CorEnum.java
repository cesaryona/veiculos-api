package br.com.veiculos.api.repository.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CorEnum {
    VERMELHO("Vermelho"),
    AZUL("Azul"),
    VERDE("Verde"),
    AMARELO("Amarelo"),
    PRETO("Preto"),
    PRATA("Prata"),
    BRANCO("Branco");

    private final String cor;
}
