package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.dto.OrganeDto;

import java.util.List;
/**
 * Interface service organe
 * @author JAF
 * @version 1.2
 */
public interface IOrganeService
{


	/**
	 * createOrgane.
	 * 
	 * @param organe
	 * @return Organe
	 * @throws Exception
	 */
	public OrganeDto createOrgane(OrganeDto organe) throws Exception;

	/**
	 * updateOrgane.
	 * 
	 * @param organe
	 * @return Organe
	 * @throws Exception
	 */
	public OrganeDto updateOrgane(OrganeDto organe) throws Exception;

	
	/**
	 * getOrganeById.
	 * 
	 * @param organeId
	 * @return Organe
	 * @throws Exception
	 */	
	public OrganeDto getOrganeById(Long organeId) throws Exception;
	
	/**
	 * findOrganesByCriteria.
	 * 
	 * @param organeCriteria
	 * @return List<Organe>
	 * @throws Exception
	 */	
	public List<OrganeDto> findOrganesByCriteria(OrganeCriteria organeCriteria) throws Exception;
	
	/**
	 * findOrganeByCriteria.
	 * 
	 * @param organeCriteria
	 * @return Organe 
	 * @throws Exception
	 */
	
	public OrganeDto findOrganeByCriteria(OrganeCriteria  organeCriteria) throws Exception;
	
	/**
	 * paginatedListOrganes.
	 * 
	 * @param organeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Organe>
	 * @throws Exception
	 */
	public List<OrganeDto> paginatedListOrganes(OrganeCriteria organeCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getOrganeDataSize.
	 * 
	 * @param organeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getOrganeDataSize(OrganeCriteria organeCriteria)  throws Exception;
	
	/**
	 * deleteOrgane.
	 * 
	 * @param organeList
	 * @throws Exception
	 */
	public void deleteOrgane(List<OrganeDto> organeList) throws Exception;
	

	/**
	 * getHistOrganeById.
	 * 
	 * @param histOrganeId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistOrganeById(Long histOrganeId) throws Exception;
	
	/**
	 * paginatedListHistOrganes.
	 * 
	 * @param histOrganeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistOrganes(HistOrganeCriteria histOrganeCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findOrganeHistsByCriteria.
	 * 
	 * @param HistOrganeCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findOrganesHistByCriteria(HistOrganeCriteria histOrganeCriteria) throws Exception;
	
	/**
	 * getHistOrganeDataSize.
	 * 
	 * @param histOrganeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistOrganeDataSize(HistOrganeCriteria histOrganeCriteria) throws Exception;


    List<Organe> findAll();
}
