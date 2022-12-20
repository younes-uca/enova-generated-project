package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;

import java.util.List;
/**
 * Interface service modaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */
public interface IModaliteRadiotherapieService
{


	/**
	 * createModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapie
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */
	public ModaliteRadiotherapieDto createModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapie) throws Exception;

	/**
	 * updateModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapie
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */
	public ModaliteRadiotherapieDto updateModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapie) throws Exception;

	
	/**
	 * getModaliteRadiotherapieById.
	 * 
	 * @param modaliteRadiotherapieId
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */	
	public ModaliteRadiotherapieDto getModaliteRadiotherapieById(Long modaliteRadiotherapieId) throws Exception;
	
	/**
	 * findModaliteRadiotherapiesByCriteria.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return List<ModaliteRadiotherapie>
	 * @throws Exception
	 */	
	public List<ModaliteRadiotherapieDto> findModaliteRadiotherapiesByCriteria(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception;
	
	/**
	 * findModaliteRadiotherapieByCriteria.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return ModaliteRadiotherapie 
	 * @throws Exception
	 */
	
	public ModaliteRadiotherapieDto findModaliteRadiotherapieByCriteria(ModaliteRadiotherapieCriteria  modaliteRadiotherapieCriteria) throws Exception;
	
	/**
	 * paginatedListModaliteRadiotherapies.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<ModaliteRadiotherapie>
	 * @throws Exception
	 */
	public List<ModaliteRadiotherapieDto> paginatedListModaliteRadiotherapies(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getModaliteRadiotherapieDataSize.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getModaliteRadiotherapieDataSize(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria)  throws Exception;
	
	/**
	 * deleteModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapieList
	 * @throws Exception
	 */
	public void deleteModaliteRadiotherapie(List<ModaliteRadiotherapieDto> modaliteRadiotherapieList) throws Exception;
	

	/**
	 * getHistModaliteRadiotherapieById.
	 * 
	 * @param histModaliteRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistModaliteRadiotherapieById(Long histModaliteRadiotherapieId) throws Exception;
	
	/**
	 * paginatedListHistModaliteRadiotherapies.
	 * 
	 * @param histModaliteRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistModaliteRadiotherapies(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findModaliteRadiotherapieHistsByCriteria.
	 * 
	 * @param HistModaliteRadiotherapieCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findModaliteRadiotherapiesHistByCriteria(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception;
	
	/**
	 * getHistModaliteRadiotherapieDataSize.
	 * 
	 * @param histModaliteRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistModaliteRadiotherapieDataSize(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception;	
		

}
