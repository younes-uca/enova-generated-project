package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dto.PersonnelDto;

import java.util.List;
/**
 * Interface service personnel
 * @author JAF
 * @version 1.2
 */
public interface IPersonnelService
{


	/**
	 * createPersonnel.
	 * 
	 * @param personnel
	 * @return Personnel
	 * @throws Exception
	 */
	public PersonnelDto createPersonnel(PersonnelDto personnel) throws Exception;

	/**
	 * updatePersonnel.
	 * 
	 * @param personnel
	 * @return Personnel
	 * @throws Exception
	 */
	public PersonnelDto updatePersonnel(PersonnelDto personnel) throws Exception;

	
	/**
	 * getPersonnelById.
	 * 
	 * @param personnelId
	 * @return Personnel
	 * @throws Exception
	 */	
	public PersonnelDto getPersonnelById(Long personnelId) throws Exception;
	
	/**
	 * findPersonnelsByCriteria.
	 * 
	 * @param personnelCriteria
	 * @return List<Personnel>
	 * @throws Exception
	 */	
	public List<PersonnelDto> findPersonnelsByCriteria(PersonnelCriteria personnelCriteria) throws Exception;
	
	/**
	 * findPersonnelByCriteria.
	 * 
	 * @param personnelCriteria
	 * @return Personnel 
	 * @throws Exception
	 */
	
	public PersonnelDto findPersonnelByCriteria(PersonnelCriteria  personnelCriteria) throws Exception;
	
	/**
	 * paginatedListPersonnels.
	 * 
	 * @param personnelCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Personnel>
	 * @throws Exception
	 */
	public List<PersonnelDto> paginatedListPersonnels(PersonnelCriteria personnelCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getPersonnelDataSize.
	 * 
	 * @param personnelCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getPersonnelDataSize(PersonnelCriteria personnelCriteria)  throws Exception;
	
	/**
	 * deletePersonnel.
	 * 
	 * @param personnelList
	 * @throws Exception
	 */
	public void deletePersonnel(List<PersonnelDto> personnelList) throws Exception;
	

	/**
	 * getHistPersonnelById.
	 * 
	 * @param histPersonnelId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistPersonnelById(Long histPersonnelId) throws Exception;
	
	/**
	 * paginatedListHistPersonnels.
	 * 
	 * @param histPersonnelCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistPersonnels(HistPersonnelCriteria histPersonnelCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findPersonnelHistsByCriteria.
	 * 
	 * @param HistPersonnelCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findPersonnelsHistByCriteria(HistPersonnelCriteria histPersonnelCriteria) throws Exception;
	
	/**
	 * getHistPersonnelDataSize.
	 * 
	 * @param histPersonnelCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistPersonnelDataSize(HistPersonnelCriteria histPersonnelCriteria) throws Exception;	
		

}
