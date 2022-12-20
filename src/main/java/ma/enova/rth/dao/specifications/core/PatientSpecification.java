package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.domain.core.Patient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PatientSpecification implements Specification<Patient> {

	private PatientCriteria criteria;
	private boolean distinct;

	public PatientSpecification(PatientCriteria criteria) {

		this.criteria = criteria;
	}

	public PatientSpecification(PatientCriteria criteria, boolean distinct) {

		this.criteria = criteria;
		this.distinct = distinct;
	}
	
	@Override
	public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
			if (criteria.getIpp() != null && !criteria.getIpp().isEmpty()) {
				predicates.add(builder.equal(root.<Long>get("ipp"), Long.parseLong(criteria.getIpp())));
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
			if (criteria.getSexe() != null && !criteria.getSexe().isEmpty()) {
				predicates.add(builder.equal(root.<String>get("sexe"), criteria.getSexe()));
			}
			if (criteria.getSexeLike() != null && !criteria.getSexeLike().isEmpty()) {
				Expression<String> path = root.<String>get("sexe");
				Expression<String> lower = builder.lower(path);
				predicates.add(builder.like(lower, "%" + criteria.getSexeLike().toLowerCase() + "%"));	
			}
			if (criteria.getDateNaissance() != null) {
				predicates.add(builder.equal(root.<LocalDate>get("dateNaissance"), criteria.getDateNaissance()));
			}
			if (criteria.getDateNaissanceFrom() != null && criteria.getDateNaissanceTo() != null) {
				predicates.add(builder.between(root.<LocalDate>get("dateNaissance"), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo()));
			} else if (criteria.getDateNaissanceFrom() != null) {
				predicates.add(builder.greaterThan(root.<LocalDate>get("dateNaissance"), criteria.getDateNaissanceFrom()));
			} else if (criteria.getDateNaissanceTo() != null) {
				predicates.add(builder.lessThan(root.<LocalDate>get("dateNaissance"), criteria.getDateNaissanceTo()));
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