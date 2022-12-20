package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;
import ma.enova.rth.common.enumeration.TYPE_VALEUR;

import java.util.List;

/**
 * Critère Parametrage.
 */
public class ParametrageCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String code;	
	private String codeLike;	
	private String valeur;	
	private String valeurLike;	
	private String typeValeur;
	private List<TYPE_VALEUR> typeValeurIn;
	private List<TYPE_VALEUR> typeValeurNotIn;
	private String description;	
	private String descriptionLike;	
	private Long categorieRoleId;
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
	public String getValeurLike() {
		return this.valeurLike;
	}
	
	public void setValeurLike(String valeurLike) {
		this.valeurLike = valeurLike;
	}
	
	public String getValeur() {
		return this.valeur;
	}
	
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getTypeValeur() {
		return this.typeValeur;
	}
	
	public void setTypeValeur(String typeValeur) {
		this.typeValeur = typeValeur;
	}

	public List<TYPE_VALEUR> getTypeValeurIn() {
		return this.typeValeurIn;
	}
	
	public void setTypeValeurIn(List<TYPE_VALEUR> typeValeurIn) {
		this.typeValeurIn = typeValeurIn;
	}
	
	public List<TYPE_VALEUR> getTypeValeurNotIn() {
		return this.typeValeurNotIn;
	}
	
	public void setTypeValeurNotIn(List<TYPE_VALEUR> typeValeurNotIn) {
		this.typeValeurNotIn = typeValeurNotIn;
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
	public Long getCategorieRoleId() {
		return this.categorieRoleId;
	}
		
	public void setCategorieRoleId(Long categorieRoleId) {
		this.categorieRoleId = categorieRoleId;
	}
	
	public Long getEtablissementId() {
		return this.etablissementId;
	}
		
	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}
	
	
}