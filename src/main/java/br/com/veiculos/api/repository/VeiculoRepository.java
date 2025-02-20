package br.com.veiculos.api.repository;

import br.com.veiculos.api.repository.criteria.VeiculoCriteriaRepository;
import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<VeiculoEntity, String>, VeiculoCriteriaRepository {

    List<VeiculoEntity> findByVendidoIsFalse();

    List<VeiculoEntity> findByMarca(final MarcaEnum marcaEnum);

    @Query("SELECT v FROM VeiculoEntity v WHERE CAST(v.criadoEm AS LocalDate) >= :criadoEm")
    List<VeiculoEntity> findByCriadoEmGreaterThanEqual(@Param("criadoEm") final LocalDate criadoEm);

    List<VeiculoEntity> findByAnoBetween(final Integer decadaInicio, final Integer decadaFim);

}
