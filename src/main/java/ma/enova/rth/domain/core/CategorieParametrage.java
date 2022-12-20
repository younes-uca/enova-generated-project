package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.BusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Cat�gorie param�trage
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "categorieparametrage_")
@GenericGenerator(name = "categorieParametrageSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "categorieParametrage_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieParametrage extends BusinessObject {

    /**
     * Fields.
     */

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
    public CategorieParametrage() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public CategorieParametrage(Long id) {
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


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "categorieParametrageSequenceGenerator")
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