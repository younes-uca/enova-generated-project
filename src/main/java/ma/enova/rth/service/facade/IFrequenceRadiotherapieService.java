package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;

import java.util.List;
/**
 * Interface service frequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */
public interface IFrequenceRadiotherapieService
{


	/**
	 * createFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapie
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */
	public FrequenceRadiotherapieDto createFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapie) throws Exception;

	/**
	 * updateFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapie
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */
	public FrequenceRadiotherapieDto updateFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapie) throws Exception;

	
	/**
	 * getFrequenceRadiotherapieById.
	 * 
	 * @param frequenceRadiotherapieId
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */	
	public FrequenceRadiotherapieDto getFrequenceRadiotherapieById(Long frequenceRadiotherapieId) throws Exception;
	
	/**
	 * findFrequenceRadiotherapiesByCriteria.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return List<FrequenceRadiotherapie>
	 * @throws Exception
	 */	
	public List<FrequenceRadiotherapieDto> findFrequenceRadiotherapiesByCriteria(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception;
	
	/**
	 * findFrequenceRadiotherapieByCriteria.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return FrequenceRadiotherapie 
	 * @throws Exception
	 */
	
	public FrequenceRadiotherapieDto findFrequenceRadiotherapieByCriteria(FrequenceRadiotherapieCriteria  frequenceRadiotherapieCriteria) throws Exception;
	
	/**
	 * paginatedListFrequenceRadiotherapies.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<FrequenceRadiotherapie>
	 * @throws Exception
	 */
	public List<FrequenceRadiotherapieDto> paginatedListFrequenceRadiotherapies(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getFrequenceRadiotherapieDataSize.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getFrequenceRadiotherapieDataSize(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria)  throws Exception;
	
	/**
	 * deleteFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapieList
	 * @throws Exception
	 */
	public void deleteFrequenceRadiotherapie(List<FrequenceRadiotherapieDto> frequenceRadiotherapieList) throws Exception;
	

	/**
	 * getHistFrequenceRadiotherapieById.
	 * 
	 * @param histFrequenceRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistFrequenceRadiotherapieById(Long histFrequenceRadiotherapieId) throws Exception;
	
	/**
	 * paginatedListHistFrequenceRadiotherapies.
	 * 
	 * @param histFrequenceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistFrequenceRadiotherapies(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findFrequenceRadiotherapieHistsByCriteria.
	 * 
	 * @param HistFrequenceRadiotherapieCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findFrequenceRadiotherapiesHistByCriteria(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception;
	
	/**
	 * getHistFrequenceRadiotherapieDataSize.
	 * 
	 * @param histFrequenceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistFrequenceRadiotherapieDataSize(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception;	
		

}
