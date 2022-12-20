package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * StatutBeneficiaire
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "frequenceradiotherapie_")
@GenericGenerator(name = "frequenceRadiotherapieSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "frequenceRadiotherapie_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrequenceRadiotherapie extends AuditBusinessObject {

    /**
     * Fields.
     */

    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Description
     */
    private String description;

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
     * Etablissement
     */
    private Etablissement etablissement;


    /**
     * Constructeur par défaut.
     */
    public FrequenceRadiotherapie() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public FrequenceRadiotherapie(Long id) {
        super(id);
    }

    /**
     * Methods.
     */

    @Column(length = 255)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(length = 255)
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Type(type = "text")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(columnDefinition = "boolean default false")
    @Type(type = "boolean")
    public boolean isActif() {
        return this.actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Column(length = 255)
    public String getHl7() {
        return this.hl7;
    }

    public void setHl7(String hl7) {
        this.hl7 = hl7;
    }

    public Long getOrdre() {
        return this.ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement_id", updatable = false)
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }


    @Id
    @Column(name = "id_frequenceradiotherapie")
    @GeneratedValue(generator = "frequenceRadiotherapieSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = libelle;
        return label;
    }


}