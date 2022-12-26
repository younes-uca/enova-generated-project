package ma.enova.rth.dao.specfication.history;

import ma.enova.rth.zynerator.specification.AbstractHistorySpecification;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.bean.historique.HistPrescriptionRadiotherapie;


public class HistPrescriptionRadiotherapieSpecification extends AbstractHistorySpecification<HistPrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapie> {

    public HistPrescriptionRadiotherapieSpecification(HistPrescriptionRadiotherapieCriteria criteria) {
        super(criteria);
    }

    public HistPrescriptionRadiotherapieSpecification(HistPrescriptionRadiotherapieCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}