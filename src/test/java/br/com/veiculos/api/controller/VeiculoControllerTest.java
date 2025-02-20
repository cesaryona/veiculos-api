package br.com.veiculos.api.controller;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.exception.handler.ResourceExceptionHandler;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import br.com.veiculos.api.service.VeiculoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class VeiculoControllerTest {

    private static final String ENDPOINT = "/veiculos";

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private VeiculoService service;

    @InjectMocks
    private VeiculoController controller;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ResourceExceptionHandler())
                .build();
    }

    @Test
    void deveChamarEndpointBuscarTodos() throws Exception {
        var id = UUID.randomUUID().toString();

        int page = 0;
        int size = 10;

        var veiculoResponse = Collections.singletonList(getVeiculoResponse(id));
        var veiculoPage = new PageImpl<>(veiculoResponse, PageRequest.of(page, size), veiculoResponse.size());

        when(service.buscarTodos(page, size)).thenReturn(veiculoPage);

        var response = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT.concat("/todos"))
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarTodos(page, size);

        assertNotNull(response);
    }

    @Test
    void buscarPorParametros() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);

        var marca = MarcaEnum.TOYOTA;
        var ano = 2022;
        var cor = CorEnum.PRETO;

        when(service.buscarPorParametros(any(), any(), any())).thenReturn(Collections.singletonList(veiculoResponse));

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/filtro"))
                                .param("marca", String.valueOf(marca))
                                .param("ano", String.valueOf(ano))
                                .param("cor", String.valueOf(cor))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarPorParametros(marca, ano, cor);

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointBuscarPorId() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);

        when(service.buscarPorId(any())).thenReturn(veiculoResponse);

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/{id}"), id)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarPorId(id);

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointBuscarVeiculosCadastradosUltimaSemana() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);

        when(service.buscarVeiculosCadastradosUltimaSemana()).thenReturn(Collections.singletonList(veiculoResponse));

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/cadastrados-ultima-semana"))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarVeiculosCadastradosUltimaSemana();

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointBuscarVeiculosNaoVendidos() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);

        when(service.buscarVeiculosNaoVendidos()).thenReturn(Collections.singletonList(veiculoResponse));

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/nao-vendidos"))
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarVeiculosNaoVendidos();

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointBuscarPorMarca() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);

        when(service.buscarPorMarca(any())).thenReturn(Collections.singletonList(veiculoResponse));

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/buscar-por-marca/{marca}"), MarcaEnum.TOYOTA)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarPorMarca(MarcaEnum.TOYOTA);

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointBuscarPorDecada() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);
        var decada = 2020;

        when(service.buscarPorDecadas(any())).thenReturn(Collections.singletonList(veiculoResponse));

        var response = mockMvc.perform(
                        MockMvcRequestBuilders.get(ENDPOINT.concat("/buscar-ultima-decada/{decada}"), decada)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).buscarPorDecadas(decada);


        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointSalvar() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoResponse = getVeiculoResponse(id);
        var request = getVeiculoRequest();

        when(service.salvar(any())).thenReturn(veiculoResponse);

        var response = mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).salvar(any(VeiculoRequest.class));

        assertNotNull(response);
    }

    @Test
    void deveChamarEndpointAtualizar() throws Exception {
        var id = UUID.randomUUID().toString();
        var veiculoRequest = getVeiculoRequest();

        doNothing().when(service).atualizar(any(), any());

        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT.concat("/{id}"), id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(veiculoRequest)))
                .andExpect(status().isNoContent());

        verify(service).atualizar(id, veiculoRequest);
    }

    @Test
    void deveChamarEndpointDeletar() throws Exception {
        var id = UUID.randomUUID().toString();

        doNothing().when(service).deletar(id);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(ENDPOINT.concat("/{id}"), id))
                .andExpect(status().isNoContent())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        verify(service).deletar(id);
    }

    private VeiculoResponse getVeiculoResponse(final String id) {
        return VeiculoResponse.builder()
                .id(id)
                .veiculo("Carro 01")
                .marca(MarcaEnum.TOYOTA.getMarca())
                .cor(CorEnum.PRETO.getCor())
                .ano(2022)
                .descricao("descricao carro")
                .vendido(false)
                .build();
    }

    private VeiculoRequest getVeiculoRequest() {
        return VeiculoRequest.builder()
                .veiculo("Carro 01")
                .marca(MarcaEnum.TOYOTA)
                .cor(CorEnum.PRETO)
                .ano(2022)
                .descricao("descricao carro")
                .vendido(false)
                .build();
    }
}