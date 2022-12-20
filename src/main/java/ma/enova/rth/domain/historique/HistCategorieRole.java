package ma.enova.rth.domain.historique;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.HistBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * HistCategorieRole
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "categorierole_hist", schema = "historique")
@GenericGenerator(name = "histCategorieRoleSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "histCategorieRole_seq"), @Parameter(name = "schema", value = "historique"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistCategorieRole extends HistBusinessObject {

	/**
	 * Fields.
	 */


	/**
	 * Constructeur par défaut.
	 */
	public HistCategorieRole() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public HistCategorieRole(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */


	@Id
	@Column(name="id_histcategorierole")
	@GeneratedValue(generator = "histCategorieRoleSequenceGenerator")
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