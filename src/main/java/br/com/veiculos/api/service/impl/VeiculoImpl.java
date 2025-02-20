package br.com.veiculos.api.service.impl;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.exception.NotFoundException;
import br.com.veiculos.api.mapper.VeiculoMapper;
import br.com.veiculos.api.repository.VeiculoRepository;
import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import br.com.veiculos.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VeiculoImpl implements VeiculoService {

    private final VeiculoMapper mapper;
    private final VeiculoRepository repository;

    @Override
    public Page<VeiculoResponse> buscarTodos(final Integer page, final Integer size) {
        var pageable = PageRequest.of(page, size);
        var veiculosPage = repository.findAll(pageable);

        var veiculoResponse = veiculosPage.getContent()
                .stream()
                .map(mapper::toResponse)
                .toList();

        return new PageImpl<>(veiculoResponse, pageable, veiculosPage.getTotalElements());
    }

    @Override
    public VeiculoResponse buscarPorId(final String id) {
        return mapper.toResponse(buscaPorId(id));
    }

    @Override
    public List<VeiculoResponse> buscarVeiculosNaoVendidos() {
        return repository.findByVendidoIsFalse()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<VeiculoResponse> buscarPorDecadas(final Integer decada) {
        var decadaInicio = (decada / 10) * 10;
        var decadaFim = decadaInicio + 9;

        System.out.println(decadaInicio);
        System.out.println(decadaFim);
        return repository.findByAnoBetween(decadaInicio, decadaFim)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<VeiculoResponse> buscarPorMarca(final MarcaEnum marcaEnum) {
        return repository.findByMarca(marcaEnum)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<VeiculoResponse> buscarVeiculosCadastradosUltimaSemana() {
        var ultimaSemana = LocalDate.now().minusWeeks(1);

        return repository.findByCriadoEmGreaterThanEqual(ultimaSemana)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public List<VeiculoResponse> buscarPorParametros(final MarcaEnum marca, final Integer ano, final CorEnum cor) {
        return repository.buscarPorParametros(marca, ano, cor)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public VeiculoResponse salvar(final VeiculoRequest request) {
        var entity = repository.save(mapper.toEntity(request));
        return mapper.toResponse(entity);
    }

    @Override
    public void atualizar(final String id, final VeiculoRequest request) {
        var entity = buscaPorId(id);

        Optional.ofNullable(request.getVeiculo()).ifPresent(entity::setVeiculo);
        Optional.ofNullable(request.getMarca()).ifPresent(entity::setMarca);
        Optional.ofNullable(request.getAno()).ifPresent(entity::setAno);
        Optional.ofNullable(request.getCor()).ifPresent(entity::setCor);
        Optional.ofNullable(request.getDescricao()).ifPresent(entity::setDescricao);
        Optional.ofNullable(request.getVendido()).ifPresent(entity::setVendido);

        repository.save(entity);
    }

    @Override
    public void deletar(final String id) {
        repository.deleteById(id);
    }

    private VeiculoEntity buscaPorId(final String id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Veiculo não encontrado - ID [%s]", id)));
    }
}
