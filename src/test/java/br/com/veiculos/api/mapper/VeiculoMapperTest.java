package br.com.veiculos.api.mapper;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        VeiculoMapperImpl.class
})
class VeiculoMapperTest {

    @Autowired
    private VeiculoMapper mapper;

    @Test
    void toEntity() {
        var request = VeiculoRequest.builder()
                .veiculo("Carro 01")
                .marca(MarcaEnum.TOYOTA)
                .cor(CorEnum.PRETO)
                .ano(2022)
                .descricao("descricao carro")
                .vendido(false)
                .build();

        var entity = mapper.toEntity(request);

        assertNotNull(entity);
        assertEquals("Carro 01", entity.getVeiculo());
        assertEquals(MarcaEnum.TOYOTA, entity.getMarca());
        assertEquals(CorEnum.PRETO, entity.getCor());
        assertEquals(2022, entity.getAno());
        assertEquals("descricao carro", entity.getDescricao());
        assertFalse(entity.getVendido());
        assertNull(entity.getId());
        assertNull(entity.getCriadoEm());
        assertNull(entity.getAtualizadoEm());
    }

    @Test
    void toResponse() {
        var id = UUID.randomUUID().toString();

        var entity = VeiculoEntity.builder()
                .id(id)
                .veiculo("Carro 01")
                .marca(MarcaEnum.TOYOTA)
                .cor(CorEnum.PRETO)
                .ano(2022)
                .descricao("descricao carro")
                .vendido(false)
                .build();

        var response = mapper.toResponse(entity);

        assertNotNull(response);
        assertEquals(id, response.getId());
        assertEquals("Carro 01", response.getVeiculo());
        assertEquals("Toyota", response.getMarca());
        assertEquals("Preto", response.getCor());
        assertEquals(2022, response.getAno());
        assertEquals("descricao carro", response.getDescricao());
        assertFalse(response.getVendido());
    }
}