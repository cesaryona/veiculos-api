package br.com.veiculos.api.repository.criteria;

import br.com.veiculos.api.repository.entity.VeiculoEntity;
import br.com.veiculos.api.repository.enums.CorEnum;
import br.com.veiculos.api.repository.enums.MarcaEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class VeiculoCriteriaRepositoryImpl implements VeiculoCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public List<VeiculoEntity> buscarPorParametros(final MarcaEnum marca, final Integer ano, final CorEnum cor) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VeiculoEntity> query = builder.createQuery(VeiculoEntity.class);
        Root<VeiculoEntity> root = query.from(VeiculoEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nonNull(marca)) {
            predicates.add(builder.equal(root.get("marca"), marca));
        }
        if (nonNull(ano)) {
            predicates.add(builder.equal(root.get("ano"), ano));
        }
        if (nonNull(cor)) {
            predicates.add(builder.equal(root.get("cor"), cor));
        }

        query.where(builder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }

}