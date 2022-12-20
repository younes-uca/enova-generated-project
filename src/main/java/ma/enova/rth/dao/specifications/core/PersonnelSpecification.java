package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.domain.core.Personnel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class PersonnelSpecification implements Specification<Personnel> {

	private PersonnelCriteria criteria;
	private boolean distinct;

	public PersonnelSpecification(PersonnelCriteria criteria) {

		this.criteria = criteria;
	}

	public PersonnelSpecification(PersonnelCriteria criteria, boolean distinct) {

		this.criteria = criteria;
		this.distinct = distinct;
	}
	
	@Override
	public Predicate toPredicate(Root<Personnel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
			if (criteria.getNom() != null && !criteria.getNom().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("nom"), criteria.getNom()));
			}
			if (criteria.getNomLike() != null && !criteria.getNomLike().isEmpty()) {
				Expression<String> path = root.<String>get("nom");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getNomLike().toLowerCase() + "%"));	
			}
			if (criteria.getPrenom() != null && !criteria.getPrenom().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("prenom"), criteria.getPrenom()));
			}
			if (criteria.getPrenomLike() != null && !criteria.getPrenomLike().isEmpty()) {
				Expression<String> path = root.<String>get("prenom");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getPrenomLike().toLowerCase() + "%"));	
			}
			if (criteria.getCin() != null && !criteria.getCin().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("cin"), criteria.getCin()));
			}
			if (criteria.getCinLike() != null && !criteria.getCinLike().isEmpty()) {
				Expression<String> path = root.<String>get("cin");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getCinLike().toLowerCase() + "%"));	
			}
			if (criteria.getAdresse() != null && !criteria.getAdresse().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("adresse"), criteria.getAdresse()));
			}
			if (criteria.getAdresseLike() != null && !criteria.getAdresseLike().isEmpty()) {
				Expression<String> path = root.<String>get("adresse");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getAdresseLike().toLowerCase() + "%"));	
			}
			if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("email"), criteria.getEmail()));
			}
			if (criteria.getEmailLike() != null && !criteria.getEmailLike().isEmpty()) {
				Expression<String> path = root.<String>get("email");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getEmailLike().toLowerCase() + "%"));	
			}
			if (criteria.getTelephone() != null && !criteria.getTelephone().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("telephone"), criteria.getTelephone()));
			}
			if (criteria.getTelephoneLike() != null && !criteria.getTelephoneLike().isEmpty()) {
				Expression<String> path = root.<String>get("telephone");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getTelephoneLike().toLowerCase() + "%"));	
			}
			if (criteria.getMobile() != null && !criteria.getMobile().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("mobile"), criteria.getMobile()));
			}
			if (criteria.getMobileLike() != null && !criteria.getMobileLike().isEmpty()) {
				Expression<String> path = root.<String>get("mobile");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getMobileLike().toLowerCase() + "%"));	
			}
			if (criteria.getExpertise() != null && !criteria.getExpertise().isEmpty()) {
				predicates.add(builder.equal(root.<Boolean>get("expertise"),Boolean.valueOf(criteria.getExpertise())));
			}			
			if (criteria.getEtablissementId() != null && criteria.getEtablissementId() > 0) {
				predicates.add(builder.equal(root.<Long>get("etablissement"), criteria.getEtablissementId()));
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