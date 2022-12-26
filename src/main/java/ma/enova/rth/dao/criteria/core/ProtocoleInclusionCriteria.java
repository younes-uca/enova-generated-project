package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.zynerator.criteria.CodeLibelleCriteria;
import ma.enova.rth.zynerator.enumeration.STATUT_PROTOCOLEINCLUSION;

import java.util.List;

/**
 * Critère ProtocoleInclusion.
 */
public class ProtocoleInclusionCriteria extends CodeLibelleCriteria {

    /**
     * Fields.
     */


    private String status;
    private List<STATUT_PROTOCOLEINCLUSION> statusIn;
    private List<STATUT_PROTOCOLEINCLUSION> statusNotIn;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<STATUT_PROTOCOLEINCLUSION> getStatusIn() {
        return statusIn;
    }

    public void setStatusIn(List<STATUT_PROTOCOLEINCLUSION> statusIn) {
        this.statusIn = statusIn;
    }

    public List<STATUT_PROTOCOLEINCLUSION> getStatusNotIn() {
        return statusNotIn;
    }

    public void setStatusNotIn(List<STATUT_PROTOCOLEINCLUSION> statusNotIn) {
        this.statusNotIn = statusNotIn;
    }
}