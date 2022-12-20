package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

import java.util.List;

/**
 * Critère Profil.
 */
public class ProfilCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String code;	
	private String codeLike;	
	private String libelle;	
	private String libelleLike;	
	private String description;	
	private String descriptionLike;	
	private List<Long> rolesSelected;



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
	public List<Long> getRolesSelected() {
		return rolesSelected;
	}

	public void setRolesSelected(List<Long> rolesSelected) {
		this.rolesSelected = rolesSelected;
	}
		
	
}