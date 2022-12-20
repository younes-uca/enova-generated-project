package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

/**
 * Critère Organe.
 */
public class OrganeCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String code;	
	private String codeLike;	
	private String libelle;	
	private String libelleLike;	
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
	public Long getEtablissementId() {
		return this.etablissementId;
	}
		
	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}
	
	
}