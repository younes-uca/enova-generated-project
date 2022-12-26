package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.ParametrageCriteria;
import ma.enova.rth.dao.criteria.history.HistParametrageCriteria;
import ma.enova.rth.dto.ParametrageDto;

import java.util.List;

/**
 * Interface service parametrage
 *
 * @author JAF
 * @version 1.2
 */
public interface IParametrageService {


    /**
     * createParametrage.
     *
     * @param parametrage
     * @return Parametrage
     * @throws Exception
     */
    ParametrageDto createParametrage(ParametrageDto parametrage) throws Exception;

    /**
     * updateParametrage.
     *
     * @param parametrage
     * @return Parametrage
     * @throws Exception
     */
    ParametrageDto updateParametrage(ParametrageDto parametrage) throws Exception;


    /**
     * getParametrageById.
     *
     * @param parametrageId
     * @return Parametrage
     * @throws Exception
     */
    ParametrageDto getParametrageById(Long parametrageId) throws Exception;

    /**
     * findParametragesByCriteria.
     *
     * @param parametrageCriteria
     * @return List<Parametrage>
     * @throws Exception
     */
    List<ParametrageDto> findParametragesByCriteria(ParametrageCriteria parametrageCriteria) throws Exception;

    /**
     * findParametrageByCriteria.
     *
     * @param parametrageCriteria
     * @return Parametrage
     * @throws Exception
     */

    ParametrageDto findParametrageByCriteria(ParametrageCriteria parametrageCriteria) throws Exception;

    /**
     * paginatedListParametrages.
     *
     * @param parametrageCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Parametrage>
     * @throws Exception
     */
    List<ParametrageDto> paginatedListParametrages(ParametrageCriteria parametrageCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * getParametrageDataSize.
     *
     * @param parametrageCriteria
     * @return int
     * @throws Exception
     */
    int getParametrageDataSize(ParametrageCriteria parametrageCriteria) throws Exception;

    /**
     * deleteParametrage.
     *
     * @param parametrageList
     * @throws Exception
     */
    void deleteParametrage(List<ParametrageDto> parametrageList) throws Exception;


    /**
     * getHistParametrageById.
     *
     * @param histParametrageId
     * @return AuditEntityDto
     * @throws Exception
     */
    AuditEntityDto getHistParametrageById(Long histParametrageId) throws Exception;

    /**
     * paginatedListHistParametrages.
     *
     * @param histParametrageCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<AuditEntityDto>
     * @throws Exception
     */

    List<AuditEntityDto> paginatedListHistParametrages(HistParametrageCriteria histParametrageCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * findParametrageHistsByCriteria.
     *
     * @param HistParametrageCriteria
     * @return List<AuditEntityDto>
     * @throws Exception
     */
    List<AuditEntityDto> findParametragesHistByCriteria(HistParametrageCriteria histParametrageCriteria) throws Exception;

    /**
     * getHistParametrageDataSize.
     *
     * @param histParametrageCriteria
     * @return int
     * @throws Exception
     */
    int getHistParametrageDataSize(HistParametrageCriteria histParametrageCriteria) throws Exception;


}
