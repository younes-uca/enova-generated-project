package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.core.Organe;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
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