package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dto.SeanceRadiotherapieDto;

import java.util.List;
/**
 * Interface service seanceRadiotherapie
 * @author JAF
 * @version 1.2
 */
public interface ISeanceRadiotherapieService
{


	/**
	 * createSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapie
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */
	public SeanceRadiotherapieDto createSeanceRadiotherapie(SeanceRadiotherapieDto seanceRadiotherapie) throws Exception;

	/**
	 * updateSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapie
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */
	public SeanceRadiotherapieDto updateSeanceRadiotherapie(SeanceRadiotherapieDto seanceRadiotherapie) throws Exception;

	
	/**
	 * getSeanceRadiotherapieById.
	 * 
	 * @param seanceRadiotherapieId
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */	
	public SeanceRadiotherapieDto getSeanceRadiotherapieById(Long seanceRadiotherapieId) throws Exception;
	
	/**
	 * findSeanceRadiotherapiesByCriteria.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return List<SeanceRadiotherapie>
	 * @throws Exception
	 */	
	public List<SeanceRadiotherapieDto> findSeanceRadiotherapiesByCriteria(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception;
	
	/**
	 * findSeanceRadiotherapieByCriteria.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return SeanceRadiotherapie 
	 * @throws Exception
	 */
	
	public SeanceRadiotherapieDto findSeanceRadiotherapieByCriteria(SeanceRadiotherapieCriteria  seanceRadiotherapieCriteria) throws Exception;
	
	/**
	 * paginatedListSeanceRadiotherapies.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<SeanceRadiotherapie>
	 * @throws Exception
	 */
	public List<SeanceRadiotherapieDto> paginatedListSeanceRadiotherapies(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getSeanceRadiotherapieDataSize.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getSeanceRadiotherapieDataSize(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria)  throws Exception;
	
	/**
	 * deleteSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapieList
	 * @throws Exception
	 */
	public void deleteSeanceRadiotherapie(List<SeanceRadiotherapieDto> seanceRadiotherapieList) throws Exception;
	

	/**
	 * getHistSeanceRadiotherapieById.
	 * 
	 * @param histSeanceRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistSeanceRadiotherapieById(Long histSeanceRadiotherapieId) throws Exception;
	
	/**
	 * paginatedListHistSeanceRadiotherapies.
	 * 
	 * @param histSeanceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistSeanceRadiotherapies(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findSeanceRadiotherapieHistsByCriteria.
	 * 
	 * @param HistSeanceRadiotherapieCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findSeanceRadiotherapiesHistByCriteria(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception;
	
	/**
	 * getHistSeanceRadiotherapieDataSize.
	 * 
	 * @param histSeanceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistSeanceRadiotherapieDataSize(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception;	
		

}
