package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.zynerator.criteria.CodeLibelleDescCriteria;

import java.util.List;

/**
 * Critère Profil.
 */
public class ProfilCriteria extends CodeLibelleDescCriteria {

    /**
     * Fields.
     */

    private List<Long> rolesSelected;

    public List<Long> getRolesSelected() {
        return rolesSelected;
    }

    public void setRolesSelected(List<Long> rolesSelected) {
        this.rolesSelected = rolesSelected;
    }
}