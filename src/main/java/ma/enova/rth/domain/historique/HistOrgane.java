package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistOrgane
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "organe_hist", schema = "historique")
@GenericGenerator(name = "histOrganeSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histOrgane_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistOrgane extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistOrgane() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistOrgane(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_historgane")
    @GeneratedValue(generator = "histOrganeSequenceGenerator")
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