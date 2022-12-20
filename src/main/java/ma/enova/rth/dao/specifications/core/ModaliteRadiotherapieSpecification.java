package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ModaliteRadiotherapieSpecification extends AbstractSpecification<ModaliteRadiotherapieCriteria, ModaliteRadiotherapie> {

    public ModaliteRadiotherapieSpecification(ModaliteRadiotherapieCriteria criteria) {
        super(criteria);
    }

    public ModaliteRadiotherapieSpecification(ModaliteRadiotherapieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<ModaliteRadiotherapie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("code", criteria.getCode(), criteria.getCodeLike());
            addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
            addPredicate("description", criteria.getDescription(), criteria.getDescriptionLike());
            addPredicate("hl7", criteria.getHl7(), criteria.getHl7Like());
            addPredicateBool("actif", criteria.getActif());
            addEtablissementPredicate();
            addOrderAndFilter();
        }
        return getResult();

    }

}