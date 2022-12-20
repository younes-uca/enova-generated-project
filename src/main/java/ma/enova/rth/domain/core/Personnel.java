package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Utilisateurs
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "personnel_")
@GenericGenerator(name = "personnelSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "personnel_seq"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Personnel extends AuditBusinessObject {

	/**
	 * Fields.
	 */

	/** Nom */
	private String nom;

	/** Prénom */
	private String prenom;

	/** CIN */
	private String cin;

	/** Adresse */
	private String adresse;

	/** E-mail */
	private String email;

	/** Téléphone */
	private String telephone;

	/** Mobile */
	private String mobile;

	/** Expertise */
	private boolean expertise = false;

	/** Etablissement */
	private Etablissement etablissement;


	/**
	 * Constructeur par défaut.
	 */
	public Personnel() {
		super();
	}

	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public Personnel(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */

	@Column(length = 255 )
	public String getNom() {
		return this.nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(length = 255 )
	public String getPrenom() {
		return this.prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@Column(length = 255 )
	public String getCin() {
		return this.cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}

	@Type(type = "text")
	public String getAdresse() {
		return this.adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(length = 255 )
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 255 )
	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(length = 255 )
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(columnDefinition = "boolean default false")
	@Type(type = "boolean")
	public boolean isExpertise() {
		return this.expertise;
	}
	public void setExpertise(boolean expertise) {
		this.expertise = expertise;
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
	@Column(name="id_personnel")
	@GeneratedValue(generator = "personnelSequenceGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public String getLabel() {
		label = nom;
		return label;
	}


}