package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.zynerator.specification.AbstractSpecification;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.bean.core.FrequenceRadiotherapie;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class FrequenceRadiotherapieSpecification extends AbstractSpecification<FrequenceRadiotherapieCriteria, FrequenceRadiotherapie> {

    public FrequenceRadiotherapieSpecification(FrequenceRadiotherapieCriteria criteria) {
        super(criteria);
    }

    public FrequenceRadiotherapieSpecification(FrequenceRadiotherapieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<FrequenceRadiotherapie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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