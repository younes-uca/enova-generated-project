package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.dto.AuditBaseDto;
import ma.enova.rth.zynerator.audit.Log;
import ma.enova.rth.zynerator.enumeration.TYPE_VALEUR;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.bean.core.CategorieParametrage;
import ma.enova.rth.bean.core.Etablissement;
import ma.enova.rth.bean.core.Parametrage;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParametrageDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Valeur
     */
    private String valeur;

    /**
     * Type valeur
     */
    private TYPE_VALEUR typeValeur;

    /**
     * Description
     */
    private String description;

    /**
     * Cat�gorie
     */
    private CategorieParametrageDto categorieRole;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public ParametrageDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ParametrageDto(Long id) {
        super(id);
    }

    public ParametrageDto(Parametrage parametrage, boolean collections, int level) {
        super();
        convertToDto(this, parametrage, collections, level);
    }

    public ParametrageDto(Parametrage parametrage, boolean collections) {
        super();
        convertToDto(this, parametrage, collections, 0);
    }

    public ParametrageDto(Parametrage parametrage) {
        super();
        convertToDto(this, parametrage, false, 0);
    }

    public Parametrage convertIdToEntity(Parametrage parametrage, ParametrageDto parametrageDto) {

        parametrage.setId(parametrageDto.getId());

        return parametrage;
    }

    public ParametrageDto convertIdToDto(ParametrageDto parametrageDto, Parametrage parametrage) {

        parametrageDto.setId(parametrage.getId());

        return parametrageDto;
    }

    public Parametrage convertToEntity(Parametrage parametrage, ParametrageDto parametrageDto) {

        if (parametrage != null) {
            parametrage = convertIdToEntity(parametrage, parametrageDto);
            parametrage.setCode(parametrageDto.getCode());
            parametrage.setValeur(parametrageDto.getValeur());
            parametrage.setTypeValeur(parametrageDto.getTypeValeur());
            parametrage.setDescription(parametrageDto.getDescription());
            parametrage.setCategorieRole(parametrageDto.getCategorieRole() != null ? new CategorieParametrage(parametrageDto.getCategorieRole().getId()) : null);
            if (parametrageDto.getEtablissement() != null && parametrageDto.getEtablissement().getId() != null)
                parametrage.setEtablissement(new Etablissement(parametrageDto.getEtablissement().getId()));

        }

        return parametrage;
    }

    public ParametrageDto mappedCustomDto(ParametrageDto parametrageDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(parametrageDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(parametrageDto, this, excludes);

            this.id = parametrageDto.getId();

            return this;
        }

        return parametrageDto;
    }

    public ParametrageDto convertToDto(ParametrageDto parametrageDto, Parametrage parametrage, boolean collections, int level) {

        level++;
        if (parametrageDto != null && level <= maxLevel) {
            parametrageDto = convertIdToDto(parametrageDto, parametrage);
            parametrageDto.setLabel(parametrage.getLabel());
            parametrageDto.setCode(parametrage.getCode());
            parametrageDto.setValeur(parametrage.getValeur());
            parametrageDto.setTypeValeur(parametrage.getTypeValeur());
            parametrageDto.setDescription(parametrage.getDescription());

            parametrageDto.setCreatedBy(parametrage.getCreatedBy());
            parametrageDto.setCreatedOn(DateUtil.dateTimeToString(parametrage.getCreatedOn()));
            parametrageDto.setUpdatedBy(parametrage.getUpdatedBy());
            parametrageDto.setUpdatedOn(DateUtil.dateTimeToString(parametrage.getUpdatedOn()));

            parametrageDto.setCategorieRole(parametrage.getCategorieRole() != null ? new CategorieParametrageDto(parametrage.getCategorieRole(), false, level) : null);
            parametrageDto.setEtablissement(parametrage.getEtablissement() != null ? new EtablissementDto(parametrage.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return parametrageDto;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Log
    public String getValeur() {
        return this.valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    @Log
    public TYPE_VALEUR getTypeValeur() {
        return typeValeur;
    }

    public void setTypeValeur(TYPE_VALEUR typeValeur) {
        this.typeValeur = typeValeur;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategorieParametrageDto getCategorieRole() {
        return this.categorieRole;
    }

    public void setCategorieRole(CategorieParametrageDto categorieRole) {
        this.categorieRole = categorieRole;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
