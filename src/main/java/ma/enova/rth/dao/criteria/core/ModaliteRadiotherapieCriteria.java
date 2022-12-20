package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

import java.util.List;

/**
 * Critère ModaliteRadiotherapie.
 */
public class ModaliteRadiotherapieCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String code;	
	private String codeLike;	
	private String libelle;	
	private String libelleLike;	
	private String description;	
	private String descriptionLike;	
	private String actif;
	private List<Long> actifAndIds;
	private String hl7;	
	private String hl7Like;	
	private String ordre;
	private Long etablissementId;



	/**
	 * Methods.
	 */

	public String getCodeLike() {
		return this.codeLike;
	}
	
	public void setCodeLike(String codeLike) {
		this.codeLike = codeLike;
	}
	
	public String getCode() {
		return this.code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getLibelleLike() {
		return this.libelleLike;
	}
	
	public void setLibelleLike(String libelleLike) {
		this.libelleLike = libelleLike;
	}
	
	public String getLibelle() {
		return this.libelle;
	}
	
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescriptionLike() {
		return this.descriptionLike;
	}
	
	public void setDescriptionLike(String descriptionLike) {
		this.descriptionLike = descriptionLike;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getActif() {
		return this.actif;
	}
	
	public void setActif(String actif) {
		this.actif = actif;
	}
	public List<Long> getActifAndIds() {
		return actifAndIds;
	}

	public void setActifAndIds(List<Long> actifAndIds) {
		this.actifAndIds = actifAndIds;
	}
	public String getHl7Like() {
		return this.hl7Like;
	}
	
	public void setHl7Like(String hl7Like) {
		this.hl7Like = hl7Like;
	}
	
	public String getHl7() {
		return this.hl7;
	}
	
	public void setHl7(String hl7) {
		this.hl7 = hl7;
	}
	public String getOrdre() {
		return this.ordre;
	}
	
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}
	public Long getEtablissementId() {
		return this.etablissementId;
	}
		
	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}
	
	
}