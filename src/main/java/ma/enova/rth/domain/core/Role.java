package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.BusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * role
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "role_")
@GenericGenerator(name = "roleSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "role_seq"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BusinessObject {

	/**
	 * Fields.
	 */

	/** Libellé */
	private String libelle;

	/** Description */
	private String description;

	/** Domaine */
	private Integer domaine = 1;

	/** categorieRole */
	private CategorieRole categorieRole;
	

	/**
	 * Constructeur par défaut.
	 */
	public Role() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public Role(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */

	@Column(length = 255 )
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
	
	@Column(columnDefinition = "integer default 1")
	@Type(type = "integer")
	public Integer getDomaine() {
		return this.domaine;
	}
	public void setDomaine(Integer domaine) {
		this.domaine = domaine;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="categorieRole_id")
	public CategorieRole getCategorieRole() {
		return this.categorieRole;
	}
		
	public void setCategorieRole(CategorieRole categorieRole) {
		this.categorieRole = categorieRole;
	}
	

	@Id
	@Column(name="id_role")
	@GeneratedValue(generator = "roleSequenceGenerator")
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