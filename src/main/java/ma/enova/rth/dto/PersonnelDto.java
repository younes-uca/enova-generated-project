package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonnelDto extends AuditBaseDto {


    /**
     * Nom
     */
    private String nom;

    /**
     * Prénom
     */
    private String prenom;

    /**
     * CIN
     */
    private String cin;

    /**
     * Adresse
     */
    private String adresse;

    /**
     * E-mail
     */
    private String email;

    /**
     * Téléphone
     */
    private String telephone;

    /**
     * Mobile
     */
    private String mobile;

    /**
     * Expertise
     */
    private boolean expertise = false;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public PersonnelDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PersonnelDto(Long id) {
        super(id);
    }

    public PersonnelDto(Personnel personnel, boolean collections, int level) {
        super();
        convertToDto(this, personnel, collections, level);
    }

    public PersonnelDto(Personnel personnel, boolean collections) {
        super();
        convertToDto(this, personnel, collections, 0);
    }

    public PersonnelDto(Personnel personnel) {
        super();
        convertToDto(this, personnel, false, 0);
    }

    public Personnel convertIdToEntity(Personnel personnel, PersonnelDto personnelDto) {

        personnel.setId(personnelDto.getId());

        return personnel;
    }

    public PersonnelDto convertIdToDto(PersonnelDto personnelDto, Personnel personnel) {

        personnelDto.setId(personnel.getId());

        return personnelDto;
    }

    public Personnel convertToEntity(Personnel personnel, PersonnelDto personnelDto) {

        if (personnel != null) {
            personnel = convertIdToEntity(personnel, personnelDto);
            personnel.setNom(personnelDto.getNom());
            personnel.setPrenom(personnelDto.getPrenom());
            personnel.setCin(personnelDto.getCin());
            personnel.setAdresse(personnelDto.getAdresse());
            personnel.setEmail(personnelDto.getEmail());
            personnel.setTelephone(personnelDto.getTelephone());
            personnel.setMobile(personnelDto.getMobile());
            personnel.setExpertise(personnelDto.isExpertise());
            if (personnelDto.getEtablissement() != null && personnelDto.getEtablissement().getId() != null)
                personnel.setEtablissement(new Etablissement(personnelDto.getEtablissement().getId()));

        }

        return personnel;
    }

    public PersonnelDto mappedCustomDto(PersonnelDto personnelDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(personnelDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(personnelDto, this, excludes);

            this.id = personnelDto.getId();

            return this;
        }

        return personnelDto;
    }

    public PersonnelDto convertToDto(PersonnelDto personnelDto, Personnel personnel, boolean collections, int level) {

        level++;
        if (personnelDto != null && level <= maxLevel) {
            personnelDto = convertIdToDto(personnelDto, personnel);
            personnelDto.setLabel(personnel.getLabel());
            personnelDto.setNom(personnel.getNom());
            personnelDto.setPrenom(personnel.getPrenom());
            personnelDto.setCin(personnel.getCin());
            personnelDto.setAdresse(personnel.getAdresse());
            personnelDto.setEmail(personnel.getEmail());
            personnelDto.setTelephone(personnel.getTelephone());
            personnelDto.setMobile(personnel.getMobile());
            personnelDto.setExpertise(personnel.isExpertise());

            personnelDto.setCreatedBy(personnel.getCreatedBy());
            personnelDto.setCreatedOn(DateUtil.dateTimeToString(personnel.getCreatedOn()));
            personnelDto.setUpdatedBy(personnel.getUpdatedBy());
            personnelDto.setUpdatedOn(DateUtil.dateTimeToString(personnel.getUpdatedOn()));

            personnelDto.setEtablissement(personnel.getEtablissement() != null ? new EtablissementDto(personnel.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return personnelDto;
    }

    @Log
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Log
    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Log
    public String getCin() {
        return this.cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Log
    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Log
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Log
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Log
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Log
    public boolean isExpertise() {
        return this.expertise;
    }

    public void setExpertise(boolean expertise) {
        this.expertise = expertise;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
