package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.common.ddd.specification.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class PersonnelSpecification extends AbstractSpecification<PersonnelCriteria, Personnel> {

    public PersonnelSpecification(PersonnelCriteria criteria) {
        super(criteria);
    }

    public PersonnelSpecification(PersonnelCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<Personnel> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("nom", criteria.getNom(), criteria.getNomLike());
            addPredicate("prenom", criteria.getPrenom(), criteria.getPrenomLike());
            addPredicate("cin", criteria.getCin(), criteria.getCinLike());
            addPredicate("adresse", criteria.getAdresse(), criteria.getAdresseLike());
            addPredicate("email", criteria.getEmail(), criteria.getEmailLike());
            addPredicate("telephone", criteria.getTelephone(), criteria.getTelephoneLike());
            addPredicate("mobile", criteria.getMobile(), criteria.getMobileLike());
            addPredicateBool("expertise", criteria.getExpertise());
            addPredicate("etablissement", criteria.getEtablissementId());
            addOrderAndFilter();
        }
        return getResult();
    }


}