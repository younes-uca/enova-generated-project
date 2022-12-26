package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.util.TreeModel;
import ma.enova.rth.dao.criteria.core.ProfilCriteria;
import ma.enova.rth.dao.criteria.history.HistProfilCriteria;
import ma.enova.rth.dto.ProfilDto;

import java.util.List;

/**
 * Interface service profil
 *
 * @author JAF
 * @version 1.2
 */
public interface IProfilService {


    /**
     * createProfil.
     *
     * @param profil
     * @return Profil
     * @throws Exception
     */
    ProfilDto createProfil(ProfilDto profil) throws Exception;

    /**
     * updateProfil.
     *
     * @param profil
     * @return Profil
     * @throws Exception
     */
    ProfilDto updateProfil(ProfilDto profil) throws Exception;


    /**
     * getProfilById.
     *
     * @param profilId
     * @return Profil
     * @throws Exception
     */
    ProfilDto getProfilById(Long profilId) throws Exception;

    /**
     * findProfilsByCriteria.
     *
     * @param profilCriteria
     * @return List<Profil>
     * @throws Exception
     */
    List<ProfilDto> findProfilsByCriteria(ProfilCriteria profilCriteria) throws Exception;

    /**
     * findProfilByCriteria.
     *
     * @param profilCriteria
     * @return Profil
     * @throws Exception
     */

    ProfilDto findProfilByCriteria(ProfilCriteria profilCriteria) throws Exception;

    /**
     * paginatedListProfils.
     *
     * @param profilCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Profil>
     * @throws Exception
     */
    List<ProfilDto> paginatedListProfils(ProfilCriteria profilCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * getProfilDataSize.
     *
     * @param profilCriteria
     * @return int
     * @throws Exception
     */
    int getProfilDataSize(ProfilCriteria profilCriteria) throws Exception;

    /**
     * deleteProfil.
     *
     * @param profilList
     * @throws Exception
     */
    void deleteProfil(List<ProfilDto> profilList) throws Exception;


    /**
     * getHistProfilById.
     *
     * @param histProfilId
     * @return AuditEntityDto
     * @throws Exception
     */
    AuditEntityDto getHistProfilById(Long histProfilId) throws Exception;

    /**
     * paginatedListHistProfils.
     *
     * @param histProfilCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<AuditEntityDto>
     * @throws Exception
     */

    List<AuditEntityDto> paginatedListHistProfils(HistProfilCriteria histProfilCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * findProfilHistsByCriteria.
     *
     * @param HistProfilCriteria
     * @return List<AuditEntityDto>
     * @throws Exception
     */
    List<AuditEntityDto> findProfilsHistByCriteria(HistProfilCriteria histProfilCriteria) throws Exception;

    /**
     * getHistProfilDataSize.
     *
     * @param histProfilCriteria
     * @return int
     * @throws Exception
     */
    int getHistProfilDataSize(HistProfilCriteria histProfilCriteria) throws Exception;

    List<TreeModel> getRolesCategorieByDomaine(Long domaine) throws Exception;

}
