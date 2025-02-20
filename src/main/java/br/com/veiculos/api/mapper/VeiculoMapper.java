package br.com.veiculos.api.mapper;

import br.com.veiculos.api.controller.request.VeiculoRequest;
import br.com.veiculos.api.controller.response.VeiculoResponse;
import br.com.veiculos.api.repository.entity.VeiculoEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true)
)
public interface VeiculoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    VeiculoEntity toEntity(final VeiculoRequest request);

    @Mapping(target = "cor", expression = "java(entity.getCor().getCor())")
    @Mapping(target = "marca", expression = "java(entity.getMarca().getMarca())")
    VeiculoResponse toResponse(final VeiculoEntity entity);
}
