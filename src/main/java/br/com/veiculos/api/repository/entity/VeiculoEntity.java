package br.com.veiculos.api.repository.entity;

import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_veiculo")
public class VeiculoEntity {

    @Id
    private String id;

    private String veiculo;

    @Enumerated(EnumType.STRING)
    private MarcaEnum marca;

    @Enumerated(EnumType.STRING)
    private CorEnum cor;

    private Integer ano;
    private String descricao;
    private Boolean vendido;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm;

    @PrePersist
    private void prePersist() {
        var dataHoraAtual = LocalDateTime.now();

        this.id = UUID.randomUUID().toString();
        this.criadoEm = dataHoraAtual;
        this.atualizadoEm = dataHoraAtual;
    }

    @PreUpdate
    private void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

}
