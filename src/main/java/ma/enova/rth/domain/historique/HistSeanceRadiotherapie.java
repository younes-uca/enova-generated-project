package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistSeanceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "seanceradiotherapie_hist", schema = "historique")
@GenericGenerator(name = "histSeanceRadiotherapieSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histSeanceRadiotherapie_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistSeanceRadiotherapie extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistSeanceRadiotherapie() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistSeanceRadiotherapie(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_histseanceradiotherapie")
    @GeneratedValue(generator = "histSeanceRadiotherapieSequenceGenerator")
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