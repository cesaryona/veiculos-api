package br.com.veiculos.api.controller.request;

import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoRequest {

    private String veiculo;
    private MarcaEnum marca;
    private CorEnum cor;
    private Integer ano;
    private String descricao;
    private Boolean vendido;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
