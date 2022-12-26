package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.dto.AuditBaseDto;
import ma.enova.rth.zynerator.audit.Log;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.bean.core.Etablissement;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EtablissementDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Actif
     */
    private boolean actif = false;

    /**
     * Hl7
     */
    private String hl7;

    /**
     * Ordre
     */
    private Long ordre;


    /**
     * Constructeur par défaut.
     */
    public EtablissementDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public EtablissementDto(Long id) {
        super(id);
    }

    public EtablissementDto(Etablissement etablissement, boolean collections, int level) {
        super();
        convertToDto(this, etablissement, collections, level);
    }

    public EtablissementDto(Etablissement etablissement, boolean collections) {
        super();
        convertToDto(this, etablissement, collections, 0);
    }

    public EtablissementDto(Etablissement etablissement) {
        super();
        convertToDto(this, etablissement, false, 0);
    }

    public Etablissement convertIdToEntity(Etablissement etablissement, EtablissementDto etablissementDto) {

        etablissement.setId(etablissementDto.getId());

        return etablissement;
    }

    public EtablissementDto convertIdToDto(EtablissementDto etablissementDto, Etablissement etablissement) {

        etablissementDto.setId(etablissement.getId());

        return etablissementDto;
    }

    public Etablissement convertToEntity(Etablissement etablissement, EtablissementDto etablissementDto) {

        if (etablissement != null) {
            etablissement = convertIdToEntity(etablissement, etablissementDto);
            etablissement.setCode(etablissementDto.getCode());
            etablissement.setActif(etablissementDto.isActif());
            etablissement.setHl7(etablissementDto.getHl7());
            etablissement.setOrdre(etablissementDto.getOrdre());

        }

        return etablissement;
    }

    public EtablissementDto mappedCustomDto(EtablissementDto etablissementDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(etablissementDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(etablissementDto, this, excludes);

            this.id = etablissementDto.getId();

            return this;
        }

        return etablissementDto;
    }

    public EtablissementDto convertToDto(EtablissementDto etablissementDto, Etablissement etablissement, boolean collections, int level) {

        level++;
        if (etablissementDto != null && level <= maxLevel) {
            etablissementDto = convertIdToDto(etablissementDto, etablissement);
            etablissementDto.setLabel(etablissement.getLabel());
            etablissementDto.setCode(etablissement.getCode());
            etablissementDto.setActif(etablissement.isActif());
            etablissementDto.setHl7(etablissement.getHl7());
            etablissementDto.setOrdre(etablissement.getOrdre());

            etablissementDto.setCreatedBy(etablissement.getCreatedBy());
            etablissementDto.setCreatedOn(DateUtil.dateTimeToString(etablissement.getCreatedOn()));
            etablissementDto.setUpdatedBy(etablissement.getUpdatedBy());
            etablissementDto.setUpdatedOn(DateUtil.dateTimeToString(etablissement.getUpdatedOn()));

            if (collections) {
            }

        }

        return etablissementDto;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Log
    public boolean isActif() {
        return this.actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Log
    public String getHl7() {
        return this.hl7;
    }

    public void setHl7(String hl7) {
        this.hl7 = hl7;
    }

    @Log
    public Long getOrdre() {
        return this.ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }


}
