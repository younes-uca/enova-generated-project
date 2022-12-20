package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.ProfilCriteria;
import ma.enova.rth.domain.core.Profil;
import ma.enova.rth.domain.core.Role;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class ProfilSpecification implements Specification<Profil> {

	private ProfilCriteria criteria;
	private boolean distinct;

	public ProfilSpecification(ProfilCriteria criteria) {

		this.criteria = criteria;
	}

	public ProfilSpecification(ProfilCriteria criteria, boolean distinct) {

		this.criteria = criteria;
		this.distinct = distinct;
	}
	
	@Override
	public Predicate toPredicate(Root<Profil> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<>();
		if (criteria != null) {
			if (criteria.getId() != null && criteria.getId() > 0) {
				predicates.add(builder.equal(root.<String>get("id"), criteria.getId()));
			}
			if (criteria.getIdsIn() != null && !criteria.getIdsIn().isEmpty()) {
				predicates.add(root.<Long>get("id").in(criteria.getIdsIn()));
			}
			if (criteria.getIdsNotIn() != null && !criteria.getIdsNotIn().isEmpty()) {
				predicates.add(builder.not(root.<Long>get("id").in(criteria.getIdsNotIn())));
			}
			if (criteria.getNotId() != null && criteria.getNotId() > 0) {
				predicates.add(builder.notEqual(root.<Boolean>get("id"), criteria.getNotId()));
			}			
			if (criteria.getCode() != null && !criteria.getCode().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("code"), criteria.getCode()));
			}
			if (criteria.getCodeLike() != null && !criteria.getCodeLike().isEmpty()) {
				Expression<String> path = root.<String>get("code");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getCodeLike().toLowerCase() + "%"));	
			}
			if (criteria.getLibelle() != null && !criteria.getLibelle().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("libelle"), criteria.getLibelle()));
			}
			if (criteria.getLibelleLike() != null && !criteria.getLibelleLike().isEmpty()) {
				Expression<String> path = root.<String>get("libelle");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getLibelleLike().toLowerCase() + "%"));	
			}
			if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("description"), criteria.getDescription()));
			}
			if (criteria.getDescriptionLike() != null && !criteria.getDescriptionLike().isEmpty()) {
				Expression<String> path = root.<String>get("description");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getDescriptionLike().toLowerCase() + "%"));	
			}
			if (criteria.getRolesSelected() != null && !criteria.getRolesSelected().isEmpty() ){
				Join<Profil, Role> join = root.join("rolesList");
				predicates.add(join.get("id").in(criteria.getRolesSelected()));				
			}
			if (criteria.getFilterName() != null && !criteria.getFilterName().isEmpty() && criteria.getFilterWord() != null && !criteria.getFilterWord().isEmpty()) {
				Expression<String> path = root.<String>get(criteria.getFilterName());
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getFilterWord().toLowerCase() + "%"));				
			}
			if (criteria.getOrderByAsc() != null && criteria.getOrderByAsc().length > 0) {
				List<Order> orderList = new ArrayList<Order>();
				for (int i = 0; i < criteria.getOrderByAsc().length; i++) {
					orderList.add(builder.asc(root.get(criteria.getOrderByAsc()[i])));
				}
				query.orderBy(orderList);
			}
			if (criteria.getOrderByDesc() != null && criteria.getOrderByDesc().length > 0) {
				List<Order> orderList = new ArrayList<Order>();
				for (int i = 0; i < criteria.getOrderByDesc().length; i++) {
					orderList.add(builder.desc(root.get(criteria.getOrderByDesc()[i])));
				}
				query.orderBy(orderList);
			}
		}
		if (distinct)
			query.distinct(true);
		return andTogether(predicates, builder);
	}

	private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
		return cb.and(predicates.toArray(new Predicate[0]));
	}
}