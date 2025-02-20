package br.com.veiculos.api.controller;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import br.com.veiculos.api.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping("/todos")
    @ResponseStatus(HttpStatus.OK)
    public Page<VeiculoResponse> buscarTodos(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        log.info("Buscando todos os veículos com paginação - Página: {}, Tamanho: {}", page, size);

        return veiculoService.buscarTodos(page, size);
    }

    @GetMapping("/filtro")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoResponse> buscarPorParametros(@RequestParam(value = "marca", required = false) final MarcaEnum marca,
                                                     @RequestParam(value = "ano", required = false) final Integer ano,
                                                     @RequestParam(value = "cor", required = false) final CorEnum cor) {
        log.info("Buscando veiculos por marca - [{}], ano - [{}], cor - [{}]", marca, ano, cor);

        return veiculoService.buscarPorParametros(marca, ano, cor);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VeiculoResponse buscarPorId(@PathVariable("id") final String id) {
        log.info("Buscando veiculo por Id - [{}]", id);

        return veiculoService.buscarPorId(id);
    }

    @GetMapping("/cadastrados-ultima-semana")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoResponse> buscarVeiculosCadastradosUltimaSemana() {
        log.info("Buscando veiculos cadastrados na última semana");

        return veiculoService.buscarVeiculosCadastradosUltimaSemana();
    }

    @GetMapping("/nao-vendidos")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoResponse> buscarVeiculosNaoVendidos() {
        log.info("Buscando veiculos não vendidos");

        return veiculoService.buscarVeiculosNaoVendidos();
    }

    @GetMapping("/buscar-por-marca/{marca}")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoResponse> buscarPorMarca(@PathVariable("marca") final MarcaEnum marcaEnum) {
        log.info("Buscando veiculo por marca - [{}]", marcaEnum);

        return veiculoService.buscarPorMarca(marcaEnum);
    }

    @GetMapping("/buscar-ultima-decada/{decada}")
    @ResponseStatus(HttpStatus.OK)
    public List<VeiculoResponse> buscarPorDecada(@PathVariable("decada") final Integer decada) {
        log.info("Buscando veiculo por decada - [{}]", decada);

        return veiculoService.buscarPorDecadas(decada);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoResponse salvar(@RequestBody final VeiculoRequest request) {
        log.info("Salvando veiculo - [{}]", request);

        return veiculoService.salvar(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable("id") final String id, @RequestBody final VeiculoRequest request) {
        log.info("Atualizando veiculo - Id [{}], request - [{}]", id, request);

        veiculoService.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") final String id) {
        log.info("Removendo veiculo por Id - [{}]", id);

        veiculoService.deletar(id);
    }
}
