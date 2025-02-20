package br.com.veiculos.api.service;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VeiculoService {

    Page<VeiculoResponse> buscarTodos(final Integer page, final Integer size);

    List<VeiculoResponse> buscarPorParametros(final MarcaEnum marca, final Integer ano, final CorEnum cor);

    VeiculoResponse buscarPorId(final String id);

    List<VeiculoResponse> buscarVeiculosNaoVendidos();

    List<VeiculoResponse> buscarPorDecadas(final Integer decada);

    List<VeiculoResponse> buscarPorMarca(final MarcaEnum marcaEnum);

    List<VeiculoResponse> buscarVeiculosCadastradosUltimaSemana();

    VeiculoResponse salvar(final VeiculoRequest request);

    void atualizar(final String id, final VeiculoRequest request);

    void deletar(final String id);

}
