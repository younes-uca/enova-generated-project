package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.BaseDto;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.domain.core.CategorieParametrage;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieParametrageDto extends BaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libelle
     */
    private String libelle;

    /**
     * Description
     */
    private String description;


    /**
     * Constructeur par défaut.
     */
    public CategorieParametrageDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public CategorieParametrageDto(Long id) {
        super(id);
    }

    public CategorieParametrageDto(CategorieParametrage categorieParametrage, boolean collections, int level) {
        super();
        convertToDto(this, categorieParametrage, collections, level);
    }

    public CategorieParametrageDto(CategorieParametrage categorieParametrage, boolean collections) {
        super();
        convertToDto(this, categorieParametrage, collections, 0);
    }

    public CategorieParametrageDto(CategorieParametrage categorieParametrage) {
        super();
        convertToDto(this, categorieParametrage, false, 0);
    }

    public CategorieParametrage convertIdToEntity(CategorieParametrage categorieParametrage, CategorieParametrageDto categorieParametrageDto) {

        categorieParametrage.setId(categorieParametrageDto.getId());

        return categorieParametrage;
    }

    public CategorieParametrageDto convertIdToDto(CategorieParametrageDto categorieParametrageDto, CategorieParametrage categorieParametrage) {

        categorieParametrageDto.setId(categorieParametrage.getId());

        return categorieParametrageDto;
    }

    public CategorieParametrage convertToEntity(CategorieParametrage categorieParametrage, CategorieParametrageDto categorieParametrageDto) {

        if (categorieParametrage != null) {
            categorieParametrage = convertIdToEntity(categorieParametrage, categorieParametrageDto);
            categorieParametrage.setCode(categorieParametrageDto.getCode());
            categorieParametrage.setLibelle(categorieParametrageDto.getLibelle());
            categorieParametrage.setDescription(categorieParametrageDto.getDescription());

        }

        return categorieParametrage;
    }

    public CategorieParametrageDto mappedCustomDto(CategorieParametrageDto categorieParametrageDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(categorieParametrageDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(categorieParametrageDto, this, excludes);

            this.id = categorieParametrageDto.getId();

            return this;
        }

        return categorieParametrageDto;
    }

    public CategorieParametrageDto convertToDto(CategorieParametrageDto categorieParametrageDto, CategorieParametrage categorieParametrage, boolean collections, int level) {

        level++;
        if (categorieParametrageDto != null && level <= maxLevel) {
            categorieParametrageDto = convertIdToDto(categorieParametrageDto, categorieParametrage);
            categorieParametrageDto.setLabel(categorieParametrage.getLabel());
            categorieParametrageDto.setCode(categorieParametrage.getCode());
            categorieParametrageDto.setLibelle(categorieParametrage.getLibelle());
            categorieParametrageDto.setDescription(categorieParametrage.getDescription());
            if (collections) {
            }

        }

        return categorieParametrageDto;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
