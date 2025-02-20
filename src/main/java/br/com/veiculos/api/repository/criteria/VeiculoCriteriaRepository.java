package br.com.veiculos.api.repository.criteria;

import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;

import java.util.List;

public interface VeiculoCriteriaRepository {

    List<VeiculoEntity> buscarPorParametros(final MarcaEnum marca, final Integer ano, final CorEnum cor);

}