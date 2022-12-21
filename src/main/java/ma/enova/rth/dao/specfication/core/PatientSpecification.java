package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.common.ddd.specification.AbstractSpecification;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.domain.core.Patient;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("ipp", criteria.getIpp());
            // addPredicate("dateNaissance", criteria.getDateNaissance(), criteria.getDateNaissanceFrom(), criteria.getDateNaissanceTo());
            addPredicate("nom", criteria.getNom(), criteria.getNomLike());
            addPredicate("prenom", criteria.getPrenom(), criteria.getPrenomLike());
            addPredicate("sexe", criteria.getSexe(), criteria.getSexeLike());
            addPredicate("etablissement", criteria.getEtablissementId());
            addOrderAndFilter();
        }
        return getResult();
    }


}