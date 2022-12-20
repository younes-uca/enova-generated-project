package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import ma.enova.rth.common.enumeration.TYPE_VALEUR;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Param�trage
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "parametrage_")
@GenericGenerator(name = "parametrageSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "parametrage_seq"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Parametrage extends AuditBusinessObject {

	/**
	 * Fields.
	 */

	/** Code */
	private String code;

	/** Valeur */
	private String valeur;

	/** Type valeur */
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "default 'TEXT'", nullable = false)	
	private TYPE_VALEUR typeValeur;

	/** Description */
	private String description;

	/** Cat�gorie */
	private CategorieParametrage categorieRole;
	
	/** Etablissement */
	private Etablissement etablissement;
	

	/**
	 * Constructeur par défaut.
	 */
	public Parametrage() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public Parametrage(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */

	@Column(length = 255 )
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(length = 255 )
	public String getValeur() {
		return this.valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "default 'TEXT'", nullable = false)	
	public TYPE_VALEUR getTypeValeur() {
		return typeValeur;
	}

	public void setTypeValeur(TYPE_VALEUR typeValeur) {
		this.typeValeur = typeValeur;
	}
	
	@Type(type = "text")
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="categorieRole_id")
	public CategorieParametrage getCategorieRole() {
		return this.categorieRole;
	}
		
	public void setCategorieRole(CategorieParametrage categorieRole) {
		this.categorieRole = categorieRole;
	}
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="etablissement_id", updatable = false)
	public Etablissement getEtablissement() {
		return this.etablissement;
	}
		
	public void setEtablissement(Etablissement etablissement) {
		this.etablissement = etablissement;
	}
	

	@Id
	@Column(name="id_parametrage")
	@GeneratedValue(generator = "parametrageSequenceGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient
	public String getLabel() {
		label = valeur;
		return label;
	}

	
}