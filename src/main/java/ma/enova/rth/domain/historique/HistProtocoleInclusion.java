package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistProtocoleInclusion
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "protocoleinclusion_hist", schema = "historique")
@GenericGenerator(name = "histProtocoleInclusionSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "histProtocoleInclusion_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistProtocoleInclusion extends HistBusinessObject {

	/**
	 * Fields.
	 */


	/**
	 * Constructeur par défaut.
	 */
	public HistProtocoleInclusion() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public HistProtocoleInclusion(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */


	@Id
	@Column(name="id_histprotocoleinclusion")
	@GeneratedValue(generator = "histProtocoleInclusionSequenceGenerator")
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