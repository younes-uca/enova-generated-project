package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.TreeModel;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.ProfilCriteria;
import ma.enova.rth.dao.criteria.history.HistProfilCriteria;
import ma.enova.rth.dao.facade.core.IProfilRepository;
import ma.enova.rth.dao.facade.core.IRoleRepository;
import ma.enova.rth.dao.facade.history.IHistProfilRepository;
import ma.enova.rth.dao.specifications.core.ProfilSpecification;
import ma.enova.rth.dao.specifications.history.HistProfilSpecification;
import ma.enova.rth.domain.core.Profil;
import ma.enova.rth.domain.historique.HistProfil;
import ma.enova.rth.dto.ProfilDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IProfilService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation du service Iprofil
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "profilService")
public class ProfilServiceImpl implements IProfilService {

    private static int counter = 0;
    @Autowired
    private IProfilRepository profilRepository;
    @Autowired
    private IHistProfilRepository histProfilRepository;
    @Autowired
    private IRoleRepository roleRepository;

    /**
     * createProfil.
     *
     * @return Profil
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProfilDto createProfil(ProfilDto profilDto) throws Exception {


        Profil profil = new Profil();
        profil = profilDto.convertToEntity(profil, profilDto);
        Profil newProfil = profilRepository.save(profil);
        profilDto.setId(newProfil.getId());
        return profilDto;
    }

    /**
     * updateProfil.
     *
     * @return Profil
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ProfilDto updateProfil(ProfilDto profilDto) throws Exception {


        saveProfilAuditData(profilDto, ACTION_TYPE.UPDATE);

        Profil profil = profilRepository.findById(profilDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Profil.class.getSimpleName(), profilDto.getId().toString()}));
        profil = profilDto.convertToEntity(profil, profilDto);
        profilRepository.saveAndFlush(profil);

        return profilDto;
    }

    /**
     * getProfilById.
     *
     * @param profilId
     * @return Profil
     * @throws Exception
     */
    public ProfilDto getProfilById(Long profilId) throws Exception {

        Profil profil = profilRepository.findById(profilId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Profil.class.getSimpleName(), profilId.toString()}));

        return new ProfilDto(profil, true, 0);

    }


    /**
     * findProfilsByCriteria.
     *
     * @param profilCriteria
     * @return List<Profil>
     * @throws Exception
     */
    public List<ProfilDto> findProfilsByCriteria(ProfilCriteria profilCriteria) throws Exception {

        Specification<Profil> specification = new ProfilSpecification(profilCriteria);

        if (profilCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, profilCriteria.getMaxResults());
            return profilRepository.findAll(specification, pageable).getContent().stream().map(profil -> new ProfilDto(profil)).collect(Collectors.toList());

        } else {
            return profilRepository.findAll(specification).stream().map(profil -> new ProfilDto(profil)).collect(Collectors.toList());
        }

    }

    /**
     * findProfilByCriteria.
     *
     * @param profilCriteria
     * @return Profil
     * @throws Exception
     */

    public ProfilDto findProfilByCriteria(ProfilCriteria profilCriteria) throws Exception {

        Specification<Profil> specification = new ProfilSpecification(profilCriteria);

        Profil profil = profilRepository.findOne(specification).orElse(null);

        ProfilDto profilDto = null;
        if (profil != null) {
            profilDto = new ProfilDto();
            return new ProfilDto(profil, true);
        }

        return profilDto;
    }

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

    public List<ProfilDto> paginatedListProfils(ProfilCriteria profilCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<Profil> specification = new ProfilSpecification(profilCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return profilRepository.findAll(specification, pageable).getContent().stream().map(profil -> new ProfilDto(profil)).collect(Collectors.toList());
    }

    /**
     * getProfilDataSize.
     *
     * @param profilCriteria
     * @return int
     * @throws Exception
     */
    public int getProfilDataSize(ProfilCriteria profilCriteria) throws Exception {

        Specification<Profil> specification = new ProfilSpecification(profilCriteria, true);

        return ((Long) profilRepository.count(specification)).intValue();

    }

    /**
     * deleteProfil.
     *
     * @param profilList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteProfil(List<ProfilDto> profilList) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (profilList != null)
            for (ProfilDto profilDto : profilList) {
                Profil toBeDeleted = profilRepository.findById(profilDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Profil.class.getSimpleName(), profilDto.getId().toString()}));

                profilRepository.delete(toBeDeleted);

                HistProfil histProfil = new HistProfil();
                histProfil.setActionType(ACTION_TYPE.DELETE.name());
                histProfil.setObjectName("profil");
                histProfil.setObjectId(profilDto.getId());
                String profilValue = new ObjectMapper().writeValueAsString(profilDto);
                histProfil.setData(profilValue);
                histProfil.setUserId(currentUser.getId());
                histProfil.setUsername(currentUser.getUsername());
                histProfil.setDate(LocalDateTime.now());
                histProfilRepository.save(histProfil);
            }
    }


    private void saveProfilAuditData(ProfilDto profil, ACTION_TYPE action) throws Exception {

        ProfilDto oldProfil = getProfilById(profil.getId());
        if (Utils.compareObjectsDiff(profil, oldProfil)) {

            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HistProfil histProfil = new HistProfil();
            histProfil.setActionType(action.name());
            histProfil.setObjectName("profil");
            histProfil.setObjectId(profil.getId());
            histProfil.setUserId(currentUser.getId());
            histProfil.setUsername(currentUser.getUsername());
            String profilValue = new ObjectMapper().writeValueAsString(profil);
            histProfil.setData(profilValue);
            histProfil.setDate(LocalDateTime.now());
            histProfilRepository.save(histProfil);
        }
    }

    /**
     * getHistProfilById.
     *
     * @param histProfilId
     * @return AuditEntityDto
     * @throws Exception
     */
    public AuditEntityDto getHistProfilById(Long histProfilId) throws Exception {

        HistProfil histProfil = histProfilRepository.findById(histProfilId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{HistProfil.class.getSimpleName(), histProfilId.toString()}));

        return new AuditEntityDto(histProfil);

    }


    /**
     * paginatedListHistProfils.
     *
     * @param histProfilCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<HistProfil>
     * @throws Exception
     */

    public List<AuditEntityDto> paginatedListHistProfils(HistProfilCriteria histProfilCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<HistProfil> specification = new HistProfilSpecification(histProfilCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return histProfilRepository.findAll(specification, pageable).getContent().stream().map(profil -> new AuditEntityDto(profil)).collect(Collectors.toList());
    }

    /**
     * findProfilsHistByCriteria.
     *
     * @return List<HistProfil>
     * @throws Exception
     */
    public List<AuditEntityDto> findProfilsHistByCriteria(HistProfilCriteria histProfilCriteria) throws Exception {

        Specification<HistProfil> specification = new HistProfilSpecification(histProfilCriteria);

        if (histProfilCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, histProfilCriteria.getMaxResults());
            return histProfilRepository.findAll(specification, pageable).getContent().stream().map(profil -> new AuditEntityDto(profil)).collect(Collectors.toList());

        } else {
            return histProfilRepository.findAll(specification).stream().map(profil -> new AuditEntityDto(profil)).collect(Collectors.toList());
        }

    }

    /**
     * getHistProfilDataSize.
     *
     * @param histProfilCriteria
     * @return int
     * @throws Exception
     */
    public int getHistProfilDataSize(HistProfilCriteria histProfilCriteria) throws Exception {

        Specification<HistProfil> specification = new HistProfilSpecification(histProfilCriteria, true);

        return ((Long) histProfilRepository.count(specification)).intValue();

    }

    public List<TreeModel> getRolesCategorieByDomaine(Long domaine) throws Exception {
        return null;

    }


}