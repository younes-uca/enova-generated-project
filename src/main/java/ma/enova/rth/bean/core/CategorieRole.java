package ma.enova.rth.bean.core;

import com.fasterxml.jackson.annotation.JsonInclude;
//import ma.enova.rth.zynerator.audit.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Catégorie rôle
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "categorierole_")
@GenericGenerator(name = "categorieRoleSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "categorieRole_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieRole extends AuditBusinessObject {

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
     * Constructeur par défaut.
     */
    public CategorieRole() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public CategorieRole(Long id) {
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


    @Id
    @Column(name = "id_categorierole")
    @GeneratedValue(generator = "categorieRoleSequenceGenerator")
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