package ma.enova.rth.bean.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.audit.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * organe
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "organe_")
@GenericGenerator(name = "organeSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "organe_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Organe extends AuditBusinessObject {

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
     * Etablissement
     */
    private Etablissement etablissement;


    /**
     * Constructeur par défaut.
     */
    public Organe() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public Organe(Long id) {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement_id", updatable = false)
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }


    @Id
    @Column(name = "id_organe")
    @GeneratedValue(generator = "organeSequenceGenerator")
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