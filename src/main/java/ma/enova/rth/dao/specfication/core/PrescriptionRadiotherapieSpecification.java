package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.common.ddd.specification.AbstractSpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class PrescriptionRadiotherapieSpecification extends AbstractSpecification<PrescriptionRadiotherapieCriteria, PrescriptionRadiotherapie> {

    public PrescriptionRadiotherapieSpecification(PrescriptionRadiotherapieCriteria criteria) {
        super(criteria);
    }

    public PrescriptionRadiotherapieSpecification(PrescriptionRadiotherapieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

    @Override
    public Predicate toPredicate(Root<PrescriptionRadiotherapie> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        attachSearchElement(root, query, builder, predicates);
        if (criteria != null) {
            addPredicateId("id", criteria.getId(), criteria.getNotId(), criteria.getIdsIn(), criteria.getIdsNotIn());
            addPredicate("datePrescription", criteria.getDatePrescription(), criteria.getDatePrescriptionFrom(), criteria.getDatePrescriptionTo());
            addPredicateInt("fractionnement", criteria.getFractionnement());
            addPredicate("dateSouhaiteDebutTraitement", criteria.getDateSouhaiteDebutTraitement(), criteria.getDateSouhaiteDebutTraitementFrom(), criteria.getDateSouhaiteDebutTraitementTo());
            addPredicate("observation", criteria.getObservation(), criteria.getObservationLike());
            addPredicate("protocoleInclusion", criteria.getProtocoleInclusionId());
            addPredicate("visee", criteria.getViseeId());
            addPredicate("medecinPrescripteur", criteria.getMedecinPrescripteurId());
            addPredicate("patient", criteria.getPatientId());
            addPredicate("getOrganeId", criteria.getOrganeId());
            addPredicate("modaliteRadiotherapie", criteria.getModaliteRadiotherapieId());
            addPredicate("frequenceRadiotherapie", criteria.getFrequenceRadiotherapieId());
            addPredicate("etablissement", criteria.getEtablissementId());
            addOrderAndFilter();
        }
        return getResult();
    }

}