package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganeDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public OrganeDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public OrganeDto(Long id) {
        super(id);
    }

    public OrganeDto(Organe organe, boolean collections, int level) {
        super();
        convertToDto(this, organe, collections, level);
    }

    public OrganeDto(Organe organe, boolean collections) {
        super();
        convertToDto(this, organe, collections, 0);
    }

    public OrganeDto(Organe organe) {
        super();
        convertToDto(this, organe, false, 0);
    }

    public Organe convertIdToEntity(Organe organe, OrganeDto organeDto) {

        organe.setId(organeDto.getId());

        return organe;
    }

    public OrganeDto convertIdToDto(OrganeDto organeDto, Organe organe) {

        organeDto.setId(organe.getId());

        return organeDto;
    }

    public Organe convertToEntity(Organe organe, OrganeDto organeDto) {

        if (organe != null) {
            organe = convertIdToEntity(organe, organeDto);
            organe.setCode(organeDto.getCode());
            organe.setLibelle(organeDto.getLibelle());
            if (organeDto.getEtablissement() != null && organeDto.getEtablissement().getId() != null)
                organe.setEtablissement(new Etablissement(organeDto.getEtablissement().getId()));

        }

        return organe;
    }

    public OrganeDto mappedCustomDto(OrganeDto organeDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(organeDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(organeDto, this, excludes);

            this.id = organeDto.getId();

            return this;
        }

        return organeDto;
    }

    public OrganeDto convertToDto(OrganeDto organeDto, Organe organe, boolean collections, int level) {

        level++;
        if (organeDto != null && level <= maxLevel) {
            organeDto = convertIdToDto(organeDto, organe);
            organeDto.setLabel(organe.getLabel());
            organeDto.setCode(organe.getCode());
            organeDto.setLibelle(organe.getLibelle());

            organeDto.setCreatedBy(organe.getCreatedBy());
            organeDto.setCreatedOn(DateUtil.dateTimeToString(organe.getCreatedOn()));
            organeDto.setUpdatedBy(organe.getUpdatedBy());
            organeDto.setUpdatedOn(DateUtil.dateTimeToString(organe.getUpdatedOn()));

            organeDto.setEtablissement(organe.getEtablissement() != null ? new EtablissementDto(organe.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return organeDto;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Log
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
