package ma.enova.rth.bean.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.history.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistVisee
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "visee_hist", schema = "historique")
@GenericGenerator(name = "histViseeSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histVisee_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistVisee extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistVisee() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistVisee(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_histvisee")
    @GeneratedValue(generator = "histViseeSequenceGenerator")
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