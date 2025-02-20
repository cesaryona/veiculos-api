package br.com.veiculos.api.service.impl;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.exception.NotFoundException;
import br.com.veiculos.api.mapper.VeiculoMapper;
import br.com.veiculos.api.repository.VeiculoRepository;
import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VeiculoImplTest {

    @Mock
    private VeiculoMapper mapper;

    @Mock
    private VeiculoRepository repository;

    @InjectMocks
    private VeiculoImpl service;

    @Test
    void deveBuscarTodos() {
        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        var pageable = PageRequest.of(0, 10);
        var pageEntity = new PageImpl<>(Collections.singletonList(entity), pageable, 1);

        when(repository.findAll(any(Pageable.class))).thenReturn(pageEntity);
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarTodos(0, 10);

        verify(repository).findAll(pageable);
        verify(mapper).toResponse(entity);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(0, response.getNumber());
        assertEquals(10, response.getSize());
    }

    @Nested
    class BuscarPorIdTest {
        @Test
        void deveBuscarPorId() {
            var id = UUID.randomUUID().toString();

            var entity = buildEntity();
            var veiculoResponse = buildVeiculoResponse();

            when(repository.findById(any())).thenReturn(Optional.of(entity));
            when(mapper.toResponse(any())).thenReturn(veiculoResponse);

            var response = service.buscarPorId(id);

            verify(repository).findById(id);
            verify(mapper).toResponse(entity);

            assertNotNull(response);
        }

        @Test
        void deveLancarExceptionQuandoNaoEncontrarVeiculoPorId() {
            var id = UUID.randomUUID().toString();

            when(repository.findById(any())).thenReturn(Optional.empty());

            var exception = assertThrows(NotFoundException.class, () -> service.buscarPorId(id));

            assertEquals(String.format("Veiculo não encontrado - ID [%s]", id), exception.getMessage());

            verify(repository).findById(id);
        }
    }

    @Test
    void deveBuscarVeiculosNaoVendidos() {
        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        when(repository.findByVendidoIsFalse()).thenReturn(Collections.singletonList(entity));
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarVeiculosNaoVendidos();

        verify(repository).findByVendidoIsFalse();
        verify(mapper).toResponse(entity);

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void deveBuscarPorDecadas() {
        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        var decada = 2010;

        when(repository.findByAnoBetween(any(), any())).thenReturn(Collections.singletonList(entity));
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarPorDecadas(decada);

        verify(repository).findByAnoBetween(decada, 2019);
        verify(mapper).toResponse(entity);

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void deveBuscarPorMarca() {
        var marca = MarcaEnum.FIAT;

        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        when(repository.findByMarca(any())).thenReturn(Collections.singletonList(entity));
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarPorMarca(marca);

        verify(repository).findByMarca(marca);
        verify(mapper).toResponse(entity);

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void deveBuscarVeiculosCadastradosUltimaSemana() {
        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        when(repository.findByCriadoEmGreaterThanEqual(any())).thenReturn(Collections.singletonList(entity));
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarVeiculosCadastradosUltimaSemana();

        verify(repository).findByCriadoEmGreaterThanEqual(LocalDate.now().minusWeeks(1));
        verify(mapper).toResponse(entity);

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void deveBuscarPorParametros() {
        var entity = buildEntity();
        var veiculoResponse = buildVeiculoResponse();

        when(repository.buscarPorParametros(any(), any(), any())).thenReturn(Collections.singletonList(entity));
        when(mapper.toResponse(any())).thenReturn(veiculoResponse);

        var response = service.buscarPorParametros(MarcaEnum.FIAT, 2020, CorEnum.PRETO);

        verify(repository).buscarPorParametros(MarcaEnum.FIAT, 2020, CorEnum.PRETO);
        verify(mapper).toResponse(entity);

        assertNotNull(response);
    }

    @Test
    void deveSalvar() {
        var request = buildVeiculoRequest();
        var entity = buildEntity();
        var resposeBody = buildVeiculoResponse();

        when(mapper.toEntity(any())).thenReturn(entity);
        when(repository.save(any())).thenReturn(entity);
        when(mapper.toResponse(any())).thenReturn(resposeBody);

        var response = service.salvar(request);

        verify(mapper).toEntity(request);
        verify(repository).save(entity);
        verify(mapper).toResponse(entity);

        assertNotNull(response);
    }

    @Test
    void deveAtualizar() {
        var id = UUID.randomUUID().toString();

        var entity = buildEntity();
        var request = buildVeiculoRequest();

        when(repository.findById(any())).thenReturn(Optional.of(entity));

        service.atualizar(id, request);

        verify(repository).findById(id);
        verify(repository).save(entity);
    }

    @Test
    void deveDeletar() {
        var id = UUID.randomUUID().toString();

        service.deletar(id);

        verify(repository).deleteById(id);
    }

    private VeiculoEntity buildEntity() {
        return VeiculoEntity.builder().build();
    }

    private VeiculoResponse buildVeiculoResponse() {
        return VeiculoResponse.builder().build();
    }

    private VeiculoRequest buildVeiculoRequest() {
        return VeiculoRequest.builder().build();
    }
}