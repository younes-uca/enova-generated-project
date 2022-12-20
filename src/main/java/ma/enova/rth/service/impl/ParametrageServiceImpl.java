package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.ParametrageCriteria;
import ma.enova.rth.dao.criteria.history.HistParametrageCriteria;
import ma.enova.rth.dao.facade.core.IParametrageRepository;
import ma.enova.rth.dao.facade.history.IHistParametrageRepository;
import ma.enova.rth.dao.specifications.core.ParametrageSpecification;
import ma.enova.rth.dao.specifications.history.HistParametrageSpecification;
import ma.enova.rth.domain.core.Parametrage;
import ma.enova.rth.domain.historique.HistParametrage;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ParametrageDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IParametrageService;
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
 * Implementation du service Iparametrage
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "parametrageService")
public class ParametrageServiceImpl implements IParametrageService {

    @Autowired
    private IParametrageRepository parametrageRepository;

    @Autowired
    private IHistParametrageRepository histParametrageRepository;


    /**
     * createParametrage.
     *
     * @param parametrage
     * @return Parametrage
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ParametrageDto createParametrage(ParametrageDto parametrageDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getEtablissement() != null)
            parametrageDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));

        Parametrage parametrage = new Parametrage();
        parametrage = parametrageDto.convertToEntity(parametrage, parametrageDto);
        Parametrage newParametrage = parametrageRepository.save(parametrage);
        parametrageDto.setId(newParametrage.getId());


        return parametrageDto;
    }

    /**
     * updateParametrage.
     *
     * @param parametrage
     * @return Parametrage
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ParametrageDto updateParametrage(ParametrageDto parametrageDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        saveParametrageAuditData(parametrageDto, ACTION_TYPE.UPDATE);

        Parametrage parametrage = parametrageRepository.findById(parametrageDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Parametrage.class.getSimpleName(), parametrageDto.getId().toString()}));
        parametrage = parametrageDto.convertToEntity(parametrage, parametrageDto);
        parametrageRepository.saveAndFlush(parametrage);

        return parametrageDto;
    }

    /**
     * getParametrageById.
     *
     * @param parametrageId
     * @return Parametrage
     * @throws Exception
     */
    public ParametrageDto getParametrageById(Long parametrageId) throws Exception {

        Parametrage parametrage = parametrageRepository.findById(parametrageId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Parametrage.class.getSimpleName(), parametrageId.toString()}));

        return new ParametrageDto(parametrage, true, 0);

    }


    /**
     * findParametragesByCriteria.
     *
     * @param parametrageCriteria
     * @return List<Parametrage>
     * @throws Exception
     */
    public List<ParametrageDto> findParametragesByCriteria(ParametrageCriteria parametrageCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        parametrageCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Parametrage> specification = new ParametrageSpecification(parametrageCriteria);

        if (parametrageCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, parametrageCriteria.getMaxResults());
            return parametrageRepository.findAll(specification, pageable).getContent().stream().map(parametrage -> new ParametrageDto(parametrage)).collect(Collectors.toList());

        } else {
            return parametrageRepository.findAll(specification).stream().map(parametrage -> new ParametrageDto(parametrage)).collect(Collectors.toList());
        }

    }

    /**
     * findParametrageByCriteria.
     *
     * @param parametrageCriteria
     * @return Parametrage
     * @throws Exception
     */

    public ParametrageDto findParametrageByCriteria(ParametrageCriteria parametrageCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        parametrageCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Parametrage> specification = new ParametrageSpecification(parametrageCriteria);

        Parametrage parametrage = parametrageRepository.findOne(specification).orElse(null);

        ParametrageDto parametrageDto = null;
        if (parametrage != null) {
            parametrageDto = new ParametrageDto();
            return new ParametrageDto(parametrage, true);
        }

        return parametrageDto;
    }

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

    public List<ParametrageDto> paginatedListParametrages(ParametrageCriteria parametrageCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        parametrageCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Parametrage> specification = new ParametrageSpecification(parametrageCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return parametrageRepository.findAll(specification, pageable).getContent().stream().map(parametrage -> new ParametrageDto(parametrage)).collect(Collectors.toList());
    }

    /**
     * getParametrageDataSize.
     *
     * @param parametrageCriteria
     * @return int
     * @throws Exception
     */
    public int getParametrageDataSize(ParametrageCriteria parametrageCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        parametrageCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Parametrage> specification = new ParametrageSpecification(parametrageCriteria, true);

        return ((Long) parametrageRepository.count(specification)).intValue();

    }

    /**
     * deleteParametrage.
     *
     * @param parametrageList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteParametrage(List<ParametrageDto> parametrageList) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (parametrageList != null)
            for (ParametrageDto parametrageDto : parametrageList) {
                Parametrage toBeDeleted = parametrageRepository.findById(parametrageDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Parametrage.class.getSimpleName(), parametrageDto.getId().toString()}));

                parametrageRepository.delete(toBeDeleted);

                HistParametrage histParametrage = new HistParametrage();
                histParametrage.setActionType(ACTION_TYPE.DELETE.name());
                histParametrage.setObjectName("parametrage");
                histParametrage.setObjectId(parametrageDto.getId());
                String parametrageValue = new ObjectMapper().writeValueAsString(parametrageDto);
                histParametrage.setData(parametrageValue);
                histParametrage.setUserId(currentUser.getId());
                histParametrage.setUsername(currentUser.getUsername());
                histParametrage.setDate(LocalDateTime.now());
                histParametrageRepository.save(histParametrage);
            }
    }


    private void saveParametrageAuditData(ParametrageDto parametrage, ACTION_TYPE action) throws Exception {

        ParametrageDto oldParametrage = getParametrageById(parametrage.getId());
        if (Utils.compareObjectsDiff(parametrage, oldParametrage)) {

            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HistParametrage histParametrage = new HistParametrage();
            histParametrage.setActionType(action.name());
            histParametrage.setObjectName("parametrage");
            histParametrage.setObjectId(parametrage.getId());
            histParametrage.setUserId(currentUser.getId());
            histParametrage.setUsername(currentUser.getUsername());
            String parametrageValue = new ObjectMapper().writeValueAsString(parametrage);
            histParametrage.setData(parametrageValue);
            histParametrage.setDate(LocalDateTime.now());
            histParametrageRepository.save(histParametrage);
        }
    }

    /**
     * getHistParametrageById.
     *
     * @param histParametrageId
     * @return AuditEntityDto
     * @throws Exception
     */
    public AuditEntityDto getHistParametrageById(Long histParametrageId) throws Exception {

        HistParametrage histParametrage = histParametrageRepository.findById(histParametrageId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{HistParametrage.class.getSimpleName(), histParametrageId.toString()}));

        return new AuditEntityDto(histParametrage);

    }


    /**
     * paginatedListHistParametrages.
     *
     * @param histParametrageCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<HistParametrage>
     * @throws Exception
     */

    public List<AuditEntityDto> paginatedListHistParametrages(HistParametrageCriteria histParametrageCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<HistParametrage> specification = new HistParametrageSpecification(histParametrageCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return histParametrageRepository.findAll(specification, pageable).getContent().stream().map(parametrage -> new AuditEntityDto(parametrage)).collect(Collectors.toList());
    }

    /**
     * findParametragesHistByCriteria.
     *
     * @param HistParametrageCriteria
     * @return List<HistParametrage>
     * @throws Exception
     */
    public List<AuditEntityDto> findParametragesHistByCriteria(HistParametrageCriteria histParametrageCriteria) throws Exception {

        Specification<HistParametrage> specification = new HistParametrageSpecification(histParametrageCriteria);

        if (histParametrageCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, histParametrageCriteria.getMaxResults());
            return histParametrageRepository.findAll(specification, pageable).getContent().stream().map(parametrage -> new AuditEntityDto(parametrage)).collect(Collectors.toList());

        } else {
            return histParametrageRepository.findAll(specification).stream().map(parametrage -> new AuditEntityDto(parametrage)).collect(Collectors.toList());
        }

    }

    /**
     * getHistParametrageDataSize.
     *
     * @param histParametrageCriteria
     * @return int
     * @throws Exception
     */
    public int getHistParametrageDataSize(HistParametrageCriteria histParametrageCriteria) throws Exception {

        Specification<HistParametrage> specification = new HistParametrageSpecification(histParametrageCriteria, true);

        return ((Long) histParametrageRepository.count(specification)).intValue();

    }

}