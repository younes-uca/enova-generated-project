package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistProfil
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "profil_hist", schema = "historique")
@GenericGenerator(name = "histProfilSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histProfil_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistProfil extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistProfil() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistProfil(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_histprofil")
    @GeneratedValue(generator = "histProfilSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = getObjectName();
        return label;
    }


}