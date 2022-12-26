package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.zynerator.specification.AbstractSpecification;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.bean.core.SeanceRadiotherapie;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class SeanceRadiotherapieSpecification extends AbstractSpecification<SeanceRadiotherapieCriteria, SeanceRadiotherapie> implements Specification<SeanceRadiotherapie> {


    public SeanceRadiotherapieSpecification(SeanceRadiotherapieCriteria criteria) {
        super(criteria);
    }

    public SeanceRadiotherapieSpecification(SeanceRadiotherapieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<SeanceRadiotherapie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("dateDebut", criteria.getDateDebut(), criteria.getDateDebutFrom(), criteria.getDateDebutTo());
            addPredicate("dateFin", criteria.getDateFin(), criteria.getDateFinFrom(), criteria.getDateFinTo());
            addPredicateBool("marquePresence", criteria.getMarquePresence());
            addPredicate("prescriptionRadiotherapie", criteria.getPrescriptionRadiotherapieId());
            addPredicate("etablissement", criteria.getEtablissementId());
            addOrderAndFilter();
        }
        return getResult();
    }
}