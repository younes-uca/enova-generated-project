package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;


/**
 * protocoleInclusion
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "protocoleinclusion_")
@GenericGenerator(name = "protocoleInclusionSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "protocoleInclusion_seq"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProtocoleInclusion extends AuditBusinessObject {

	/**
	 * Fields.
	 */

	/** Code */
	private String code;

	/** Libellé */
	private String libelle;

	/** Status */
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "default 'PRESCRIPTION'", nullable = false)	
	private STATUT_PROTOCOLEINCLUSION status;

	/** Etablissement */
	private Etablissement etablissement;
	

	/**
	 * Constructeur par défaut.
	 */
	public ProtocoleInclusion() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public ProtocoleInclusion(Long id) {
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
	public String getLibelle() {
		return this.libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "default 'PRESCRIPTION'", nullable = false)	
	public STATUT_PROTOCOLEINCLUSION getStatus() {
		return status;
	}

	public void setStatus(STATUT_PROTOCOLEINCLUSION status) {
		this.status = status;
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
	@Column(name="id_protocoleinclusion")
	@GeneratedValue(generator = "protocoleInclusionSequenceGenerator")
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