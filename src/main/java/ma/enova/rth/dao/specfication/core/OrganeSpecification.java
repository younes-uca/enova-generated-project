package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.common.ddd.specification.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class OrganeSpecification extends AbstractSpecification<OrganeCriteria, Organe> {

    public OrganeSpecification(OrganeCriteria criteria) {
        super(criteria);
    }

    public OrganeSpecification(OrganeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<Organe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("code", criteria.getCode(), criteria.getCodeLike());
            addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
            addEtablissementPredicate();
            addOrderAndFilter();
        }
        return getResult();

    }
}