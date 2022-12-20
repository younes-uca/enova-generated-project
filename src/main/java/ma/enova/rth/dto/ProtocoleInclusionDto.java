package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProtocoleInclusionDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Status
     */
    private STATUT_PROTOCOLEINCLUSION status;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public ProtocoleInclusionDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ProtocoleInclusionDto(Long id) {
        super(id);
    }

    public ProtocoleInclusionDto(ProtocoleInclusion protocoleInclusion, boolean collections, int level) {
        super();
        convertToDto(this, protocoleInclusion, collections, level);
    }

    public ProtocoleInclusionDto(ProtocoleInclusion protocoleInclusion, boolean collections) {
        super();
        convertToDto(this, protocoleInclusion, collections, 0);
    }

    public ProtocoleInclusionDto(ProtocoleInclusion protocoleInclusion) {
        super();
        convertToDto(this, protocoleInclusion, false, 0);
    }

    public ProtocoleInclusion convertIdToEntity(ProtocoleInclusion protocoleInclusion, ProtocoleInclusionDto protocoleInclusionDto) {

        protocoleInclusion.setId(protocoleInclusionDto.getId());

        return protocoleInclusion;
    }

    public ProtocoleInclusionDto convertIdToDto(ProtocoleInclusionDto protocoleInclusionDto, ProtocoleInclusion protocoleInclusion) {

        protocoleInclusionDto.setId(protocoleInclusion.getId());

        return protocoleInclusionDto;
    }

    public ProtocoleInclusion convertToEntity(ProtocoleInclusion protocoleInclusion, ProtocoleInclusionDto protocoleInclusionDto) {

        if (protocoleInclusion != null) {
            protocoleInclusion = convertIdToEntity(protocoleInclusion, protocoleInclusionDto);
            protocoleInclusion.setCode(protocoleInclusionDto.getCode());
            protocoleInclusion.setLibelle(protocoleInclusionDto.getLibelle());
            protocoleInclusion.setStatus(protocoleInclusionDto.getStatus());
            if (protocoleInclusionDto.getEtablissement() != null && protocoleInclusionDto.getEtablissement().getId() != null)
                protocoleInclusion.setEtablissement(new Etablissement(protocoleInclusionDto.getEtablissement().getId()));

        }

        return protocoleInclusion;
    }

    public ProtocoleInclusionDto mappedCustomDto(ProtocoleInclusionDto protocoleInclusionDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(protocoleInclusionDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(protocoleInclusionDto, this, excludes);

            this.id = protocoleInclusionDto.getId();

            return this;
        }

        return protocoleInclusionDto;
    }

    public ProtocoleInclusionDto convertToDto(ProtocoleInclusionDto protocoleInclusionDto, ProtocoleInclusion protocoleInclusion, boolean collections, int level) {

        level++;
        if (protocoleInclusionDto != null && level <= maxLevel) {
            protocoleInclusionDto = convertIdToDto(protocoleInclusionDto, protocoleInclusion);
            protocoleInclusionDto.setLabel(protocoleInclusion.getLabel());
            protocoleInclusionDto.setCode(protocoleInclusion.getCode());
            protocoleInclusionDto.setLibelle(protocoleInclusion.getLibelle());
            protocoleInclusionDto.setStatus(protocoleInclusion.getStatus());

            protocoleInclusionDto.setCreatedBy(protocoleInclusion.getCreatedBy());
            protocoleInclusionDto.setCreatedOn(DateUtil.dateTimeToString(protocoleInclusion.getCreatedOn()));
            protocoleInclusionDto.setUpdatedBy(protocoleInclusion.getUpdatedBy());
            protocoleInclusionDto.setUpdatedOn(DateUtil.dateTimeToString(protocoleInclusion.getUpdatedOn()));

            protocoleInclusionDto.setEtablissement(protocoleInclusion.getEtablissement() != null ? new EtablissementDto(protocoleInclusion.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return protocoleInclusionDto;
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
    public STATUT_PROTOCOLEINCLUSION getStatus() {
        return status;
    }

    public void setStatus(STATUT_PROTOCOLEINCLUSION status) {
        this.status = status;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
