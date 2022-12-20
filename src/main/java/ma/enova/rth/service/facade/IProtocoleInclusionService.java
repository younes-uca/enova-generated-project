package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dto.ProtocoleInclusionDto;

import java.util.List;
/**
 * Interface service protocoleInclusion
 * @author JAF
 * @version 1.2
 */
public interface IProtocoleInclusionService
{


	/**
	 * createProtocoleInclusion.
	 * 
	 * @param protocoleInclusion
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */
	public ProtocoleInclusionDto createProtocoleInclusion(ProtocoleInclusionDto protocoleInclusion) throws Exception;

	/**
	 * updateProtocoleInclusion.
	 * 
	 * @param protocoleInclusion
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */
	public ProtocoleInclusionDto updateProtocoleInclusion(ProtocoleInclusionDto protocoleInclusion) throws Exception;

	
	/**
	 * getProtocoleInclusionById.
	 * 
	 * @param protocoleInclusionId
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */	
	public ProtocoleInclusionDto getProtocoleInclusionById(Long protocoleInclusionId) throws Exception;
	
	/**
	 * findProtocoleInclusionsByCriteria.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return List<ProtocoleInclusion>
	 * @throws Exception
	 */	
	public List<ProtocoleInclusionDto> findProtocoleInclusionsByCriteria(ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception;
	
	/**
	 * findProtocoleInclusionByCriteria.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return ProtocoleInclusion 
	 * @throws Exception
	 */
	
	public ProtocoleInclusionDto findProtocoleInclusionByCriteria(ProtocoleInclusionCriteria  protocoleInclusionCriteria) throws Exception;
	
	/**
	 * paginatedListProtocoleInclusions.
	 * 
	 * @param protocoleInclusionCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<ProtocoleInclusion>
	 * @throws Exception
	 */
	public List<ProtocoleInclusionDto> paginatedListProtocoleInclusions(ProtocoleInclusionCriteria protocoleInclusionCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getProtocoleInclusionDataSize.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getProtocoleInclusionDataSize(ProtocoleInclusionCriteria protocoleInclusionCriteria)  throws Exception;
	
	/**
	 * deleteProtocoleInclusion.
	 * 
	 * @param protocoleInclusionList
	 * @throws Exception
	 */
	public void deleteProtocoleInclusion(List<ProtocoleInclusionDto> protocoleInclusionList) throws Exception;
	

	/**
	 * getHistProtocoleInclusionById.
	 * 
	 * @param histProtocoleInclusionId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistProtocoleInclusionById(Long histProtocoleInclusionId) throws Exception;
	
	/**
	 * paginatedListHistProtocoleInclusions.
	 * 
	 * @param histProtocoleInclusionCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistProtocoleInclusions(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findProtocoleInclusionHistsByCriteria.
	 * 
	 * @param HistProtocoleInclusionCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findProtocoleInclusionsHistByCriteria(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception;
	
	/**
	 * getHistProtocoleInclusionDataSize.
	 * 
	 * @param histProtocoleInclusionCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistProtocoleInclusionDataSize(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception;	
		

}
