package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dto.ViseeDto;

import java.util.List;
/**
 * Interface service visee
 * @author JAF
 * @version 1.2
 */
public interface IViseeService
{


	/**
	 * createVisee.
	 * 
	 * @param visee
	 * @return Visee
	 * @throws Exception
	 */
	public ViseeDto createVisee(ViseeDto visee) throws Exception;

	/**
	 * updateVisee.
	 * 
	 * @param visee
	 * @return Visee
	 * @throws Exception
	 */
	public ViseeDto updateVisee(ViseeDto visee) throws Exception;

	
	/**
	 * getViseeById.
	 * 
	 * @param viseeId
	 * @return Visee
	 * @throws Exception
	 */	
	public ViseeDto getViseeById(Long viseeId) throws Exception;
	
	/**
	 * findViseesByCriteria.
	 * 
	 * @param viseeCriteria
	 * @return List<Visee>
	 * @throws Exception
	 */	
	public List<ViseeDto> findViseesByCriteria(ViseeCriteria viseeCriteria) throws Exception;
	
	/**
	 * findViseeByCriteria.
	 * 
	 * @param viseeCriteria
	 * @return Visee 
	 * @throws Exception
	 */
	
	public ViseeDto findViseeByCriteria(ViseeCriteria  viseeCriteria) throws Exception;
	
	/**
	 * paginatedListVisees.
	 * 
	 * @param viseeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Visee>
	 * @throws Exception
	 */
	public List<ViseeDto> paginatedListVisees(ViseeCriteria viseeCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getViseeDataSize.
	 * 
	 * @param viseeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getViseeDataSize(ViseeCriteria viseeCriteria)  throws Exception;
	
	/**
	 * deleteVisee.
	 * 
	 * @param viseeList
	 * @throws Exception
	 */
	public void deleteVisee(List<ViseeDto> viseeList) throws Exception;
	

	/**
	 * getHistViseeById.
	 * 
	 * @param histViseeId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistViseeById(Long histViseeId) throws Exception;
	
	/**
	 * paginatedListHistVisees.
	 * 
	 * @param histViseeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistVisees(HistViseeCriteria histViseeCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findViseeHistsByCriteria.
	 * 
	 * @param HistViseeCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findViseesHistByCriteria(HistViseeCriteria histViseeCriteria) throws Exception;
	
	/**
	 * getHistViseeDataSize.
	 * 
	 * @param histViseeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistViseeDataSize(HistViseeCriteria histViseeCriteria) throws Exception;	
		

}
