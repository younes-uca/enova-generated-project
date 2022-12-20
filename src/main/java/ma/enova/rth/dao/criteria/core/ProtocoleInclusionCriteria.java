package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;
import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;

import java.util.List;

/**
 * Critère ProtocoleInclusion.
 */
public class ProtocoleInclusionCriteria extends BaseCriteria{

	/**
	 * Fields.
	 */

	private String code;	
	private String codeLike;	
	private String libelle;	
	private String libelleLike;	
	private String status;
	private List<STATUT_PROTOCOLEINCLUSION> statusIn;
	private List<STATUT_PROTOCOLEINCLUSION> statusNotIn;
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
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public List<STATUT_PROTOCOLEINCLUSION> getStatusIn() {
		return this.statusIn;
	}
	
	public void setStatusIn(List<STATUT_PROTOCOLEINCLUSION> statusIn) {
		this.statusIn = statusIn;
	}
	
	public List<STATUT_PROTOCOLEINCLUSION> getStatusNotIn() {
		return this.statusNotIn;
	}
	
	public void setStatusNotIn(List<STATUT_PROTOCOLEINCLUSION> statusNotIn) {
		this.statusNotIn = statusNotIn;
	}	
	public Long getEtablissementId() {
		return this.etablissementId;
	}
		
	public void setEtablissementId(Long etablissementId) {
		this.etablissementId = etablissementId;
	}
	
	
}