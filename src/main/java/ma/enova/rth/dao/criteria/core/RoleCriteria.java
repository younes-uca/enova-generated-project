package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

/**
 * Critère Role.
 */
public class RoleCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String libelle;	
	private String libelleLike;	
	private String description;	
	private String descriptionLike;	
	private String domaine;
	private Long categorieRoleId;



	/**
	 * Methods.
	 */

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
	public String getDomaine() {
		return this.domaine;
	}
	
	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}
	public Long getCategorieRoleId() {
		return this.categorieRoleId;
	}
		
	public void setCategorieRoleId(Long categorieRoleId) {
		this.categorieRoleId = categorieRoleId;
	}
	
	
}