package ma.enova.rth.bean.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.history.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistPatient
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "patient_hist", schema = "historique")
@GenericGenerator(name = "histPatientSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histPatient_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistPatient extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistPatient() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistPatient(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_histpatient")
    @GeneratedValue(generator = "histPatientSequenceGenerator")
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