package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.EtablissementCriteria;
import ma.enova.rth.dao.criteria.history.HistEtablissementCriteria;
import ma.enova.rth.dto.EtablissementDto;

import java.util.List;
/**
 * Interface service etablissement
 * @author JAF
 * @version 1.2
 */
public interface IEtablissementService
{


	/**
	 * createEtablissement.
	 * 
	 * @param etablissement
	 * @return Etablissement
	 * @throws Exception
	 */
	public EtablissementDto createEtablissement(EtablissementDto etablissement) throws Exception;

	/**
	 * updateEtablissement.
	 * 
	 * @param etablissement
	 * @return Etablissement
	 * @throws Exception
	 */
	public EtablissementDto updateEtablissement(EtablissementDto etablissement) throws Exception;

	
	/**
	 * getEtablissementById.
	 * 
	 * @param etablissementId
	 * @return Etablissement
	 * @throws Exception
	 */	
	public EtablissementDto getEtablissementById(Long etablissementId) throws Exception;
	
	/**
	 * findEtablissementsByCriteria.
	 * 
	 * @param etablissementCriteria
	 * @return List<Etablissement>
	 * @throws Exception
	 */	
	public List<EtablissementDto> findEtablissementsByCriteria(EtablissementCriteria etablissementCriteria) throws Exception;
	
	/**
	 * findEtablissementByCriteria.
	 * 
	 * @param etablissementCriteria
	 * @return Etablissement 
	 * @throws Exception
	 */
	
	public EtablissementDto findEtablissementByCriteria(EtablissementCriteria  etablissementCriteria) throws Exception;
	
	/**
	 * paginatedListEtablissements.
	 * 
	 * @param etablissementCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Etablissement>
	 * @throws Exception
	 */
	public List<EtablissementDto> paginatedListEtablissements(EtablissementCriteria etablissementCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getEtablissementDataSize.
	 * 
	 * @param etablissementCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getEtablissementDataSize(EtablissementCriteria etablissementCriteria)  throws Exception;
	
	/**
	 * deleteEtablissement.
	 * 
	 * @param etablissementList
	 * @throws Exception
	 */
	public void deleteEtablissement(List<EtablissementDto> etablissementList) throws Exception;
	

	/**
	 * getHistEtablissementById.
	 * 
	 * @param histEtablissementId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistEtablissementById(Long histEtablissementId) throws Exception;
	
	/**
	 * paginatedListHistEtablissements.
	 * 
	 * @param histEtablissementCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistEtablissements(HistEtablissementCriteria histEtablissementCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findEtablissementHistsByCriteria.
	 * 
	 * @param HistEtablissementCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findEtablissementsHistByCriteria(HistEtablissementCriteria histEtablissementCriteria) throws Exception;
	
	/**
	 * getHistEtablissementDataSize.
	 * 
	 * @param histEtablissementCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistEtablissementDataSize(HistEtablissementCriteria histEtablissementCriteria) throws Exception;	
		

}
