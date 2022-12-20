package ma.enova.rth.dao.specifications.core;

import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.core.Patient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.rowset.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PatientSpecification extends AbstractSpecification<PatientCriteria, Patient> {

	public PatientSpecification(PatientCriteria criteria) {
		super(criteria);
	}

	public PatientSpecification(PatientCriteria criteria, boolean distinct) {
		super(criteria, distinct);
	}

	@Override
	public  Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
		attachSearchElement(root, query, builder, predicates);
		if (criteria != null) {
			addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
			lolo
			addPredicate("ipp", criteria.getIpp());
			addPredicate("dateNaissance", criteria.getDateNaissance(), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo());
			addPredicate("nom", criteria.getNom(), criteria.getNomLike());
			addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
			addPredicate("sexe", criteria.getSexe(),criteria.getSexeLike());
			addPredicate("etablissement", criteria.getEtablissementId());
			addOrderAndFilter();
		}
		return getResult();
	}




}