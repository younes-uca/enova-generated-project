package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistEtablissement
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "etablissement_hist", schema = "historique")
@GenericGenerator(name = "histEtablissementSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "histEtablissement_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistEtablissement extends HistBusinessObject {

    /**
     * Fields.
     */


    /**
     * Constructeur par défaut.
     */
    public HistEtablissement() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public HistEtablissement(Long id) {
        super(id);
    }

    /**
     * Methods.
     */


    @Id
    @Column(name = "id_histetablissement")
    @GeneratedValue(generator = "histEtablissementSequenceGenerator")
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