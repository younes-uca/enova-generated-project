package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.UtilisateurCriteria;
import ma.enova.rth.dao.criteria.history.HistUtilisateurCriteria;
import ma.enova.rth.bean.core.Utilisateur;
import ma.enova.rth.dto.UtilisateurDto;

import java.util.List;

/**
 * Interface service utilisateur
 *
 * @author JAF
 * @version 1.2
 */
public interface IUtilisateurService {


    /**
     * createUtilisateur.
     *
     * @param utilisateur
     * @return Utilisateur
     * @throws Exception
     */
    UtilisateurDto createUtilisateur(UtilisateurDto utilisateur) throws Exception;

    /**
     * updateUtilisateur.
     *
     * @param utilisateur
     * @return Utilisateur
     * @throws Exception
     */
    UtilisateurDto updateUtilisateur(UtilisateurDto utilisateur) throws Exception;


    /**
     * getUtilisateurById.
     *
     * @param utilisateurId
     * @return Utilisateur
     * @throws Exception
     */
    UtilisateurDto getUtilisateurById(Long utilisateurId) throws Exception;

    /**
     * findUtilisateursByCriteria.
     *
     * @param utilisateurCriteria
     * @return List<Utilisateur>
     * @throws Exception
     */
    List<UtilisateurDto> findUtilisateursByCriteria(UtilisateurCriteria utilisateurCriteria) throws Exception;

    /**
     * findUtilisateurByCriteria.
     *
     * @param utilisateurCriteria
     * @return Utilisateur
     * @throws Exception
     */

    UtilisateurDto findUtilisateurByCriteria(UtilisateurCriteria utilisateurCriteria) throws Exception;

    /**
     * paginatedListUtilisateurs.
     *
     * @param utilisateurCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Utilisateur>
     * @throws Exception
     */
    List<UtilisateurDto> paginatedListUtilisateurs(UtilisateurCriteria utilisateurCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * getUtilisateurDataSize.
     *
     * @param utilisateurCriteria
     * @return int
     * @throws Exception
     */
    int getUtilisateurDataSize(UtilisateurCriteria utilisateurCriteria) throws Exception;

    /**
     * deleteUtilisateur.
     *
     * @param utilisateurList
     * @throws Exception
     */
    void deleteUtilisateur(List<UtilisateurDto> utilisateurList) throws Exception;

    /**
     * resetUtilisateurPassword.
     *
     * @param utilisateurId
     * @return utilisateur
     * @throws Exception
     */
    UtilisateurDto resetUtilisateurPassword(Long utilisateurId) throws Exception;

    /**
     * updateUtilisateurPassword.
     *
     * @param utilisateur
     * @return utilisateur
     * @throws Exception
     */
    UtilisateurDto updateUtilisateurPassword(UtilisateurDto utilisateur) throws Exception;

    /**
     * loadUserByUsername.
     *
     * @param username
     * @return utilisateur
     * @throws Exception
     */
    Utilisateur loadUserByUsername(String username) throws Exception;

    /**
     * loadUserByUsername.
     *
     * @param username
     * @return utilisateur
     * @throws Exception
     */
    List<String> getCategorieRoleUtilisateur(Long id) throws Exception;

    /**
     * getHistUtilisateurById.
     *
     * @param histUtilisateurId
     * @return AuditEntityDto
     * @throws Exception
     */
    AuditEntityDto getHistUtilisateurById(Long histUtilisateurId) throws Exception;

    /**
     * paginatedListHistUtilisateurs.
     *
     * @param histUtilisateurCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<AuditEntityDto>
     * @throws Exception
     */

    List<AuditEntityDto> paginatedListHistUtilisateurs(HistUtilisateurCriteria histUtilisateurCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * findUtilisateurHistsByCriteria.
     *
     * @param HistUtilisateurCriteria
     * @return List<AuditEntityDto>
     * @throws Exception
     */
    List<AuditEntityDto> findUtilisateursHistByCriteria(HistUtilisateurCriteria histUtilisateurCriteria) throws Exception;

    /**
     * getHistUtilisateurDataSize.
     *
     * @param histUtilisateurCriteria
     * @return int
     * @throws Exception
     */
    int getHistUtilisateurDataSize(HistUtilisateurCriteria histUtilisateurCriteria) throws Exception;


}
