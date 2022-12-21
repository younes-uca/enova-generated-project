package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.common.ddd.specification.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class ProtocoleInclusionSpecification extends AbstractSpecification<ProtocoleInclusionCriteria, ProtocoleInclusion> {

    public ProtocoleInclusionSpecification(ProtocoleInclusionCriteria criteria) {
        super(criteria);
    }

    public ProtocoleInclusionSpecification(ProtocoleInclusionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<ProtocoleInclusion> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("code", criteria.getCode(), criteria.getCodeLike());
            addPredicate("libelle", criteria.getLibelle(), criteria.getLibelleLike());
            if (StringUtil.isNotEmpty(criteria.getStatus())) {
                predicates.add(builder.equal(root.<STATUT_PROTOCOLEINCLUSION>get("status"), STATUT_PROTOCOLEINCLUSION.valueOf(criteria.getStatus())));
            }
            if (criteria.getStatusIn() != null && !criteria.getStatusIn().isEmpty()) {
                predicates.add(root.<STATUT_PROTOCOLEINCLUSION>get("status").in(criteria.getStatusIn()));
            }
            if (criteria.getStatusNotIn() != null && !criteria.getStatusNotIn().isEmpty()) {
                predicates.add(builder.not(root.<STATUT_PROTOCOLEINCLUSION>get("status").in(criteria.getStatusNotIn())));
            }

            addEtablissementPredicate();
            addOrderAndFilter();
        }
        return getResult();

    }


}