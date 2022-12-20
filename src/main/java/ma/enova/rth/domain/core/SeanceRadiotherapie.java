package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * seanceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Entity
@Table(name = "seanceradiotherapie_")
@GenericGenerator(name = "seanceRadiotherapieSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = { @Parameter(name = "sequence_name", value = "seanceRadiotherapie_seq"), @Parameter(name = "increment_size", value = "1") })
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeanceRadiotherapie extends AuditBusinessObject {

	/**
	 * Fields.
	 */

	/** Date début */
	private LocalDateTime dateDebut;

	/** Date de fin */
	private LocalDateTime dateFin;

	/** MarquePresence */
	private boolean marquePresence = false;

	/** prescriptionRadiotherapie */
	private PrescriptionRadiotherapie prescriptionRadiotherapie;
	
	/** Etablissement */
	private Etablissement etablissement;
	

	/**
	 * Constructeur par défaut.
	 */
	public SeanceRadiotherapie() {
		super();
	}
	
	/**
	 * Constructeur.
	 * @param id clé primaire
	 */
	public SeanceRadiotherapie(Long id) {
		super(id);
	}

	/**
	 * Methods.
	 */

	public LocalDateTime getDateDebut() {
		return this.dateDebut;
	}
	public void setDateDebut(LocalDateTime dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	public LocalDateTime getDateFin() {
		return this.dateFin;
	}
	public void setDateFin(LocalDateTime dateFin) {
		this.dateFin = dateFin;
	}
	
	@Column(columnDefinition = "boolean default false")
	@Type(type = "boolean")
	public boolean isMarquePresence() {
		return this.marquePresence;
	}
	public void setMarquePresence(boolean marquePresence) {
		this.marquePresence = marquePresence;
	}
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name="prescriptionRadiotherapie_id")
	public PrescriptionRadiotherapie getPrescriptionRadiotherapie() {
		return this.prescriptionRadiotherapie;
	}
		
	public void setPrescriptionRadiotherapie(PrescriptionRadiotherapie prescriptionRadiotherapie) {
		this.prescriptionRadiotherapie = prescriptionRadiotherapie;
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
	@Column(name="id_seanceradiotherapie")
	@GeneratedValue(generator = "seanceRadiotherapieSequenceGenerator")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient
	public String getLabel() {
		label = super.getLabel();
		return label;
	}

	
}