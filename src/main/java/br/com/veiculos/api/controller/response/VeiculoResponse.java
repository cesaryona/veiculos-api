package br.com.veiculos.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoResponse {

    private String id;
    private String veiculo;
    private String marca;
    private String cor;
    private Integer ano;
    private String descricao;
    private Boolean vendido;

}
