package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.EtablissementCriteria;
import ma.enova.rth.dao.criteria.history.HistEtablissementCriteria;
import ma.enova.rth.dao.facade.core.IEtablissementRepository;
import ma.enova.rth.dao.facade.history.IHistEtablissementRepository;
import ma.enova.rth.dao.specfication.core.EtablissementSpecification;
import ma.enova.rth.dao.specfication.history.HistEtablissementSpecification;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.historique.HistEtablissement;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IEtablissementService;
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
 * Implementation du service Ietablissement
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "etablissementService")
public class EtablissementServiceImpl implements IEtablissementService {

    @Autowired
    private IEtablissementRepository etablissementRepository;

    @Autowired
    private IHistEtablissementRepository histEtablissementRepository;


    /**
     * createEtablissement.
     *
     * @param etablissement
     * @return Etablissement
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtablissementDto createEtablissement(EtablissementDto etablissementDto) throws Exception {


        Etablissement etablissement = new Etablissement();
        etablissement = etablissementDto.convertToEntity(etablissement, etablissementDto);
        Etablissement newEtablissement = etablissementRepository.save(etablissement);
        etablissementDto.setId(newEtablissement.getId());


        return etablissementDto;
    }

    /**
     * updateEtablissement.
     *
     * @param etablissement
     * @return Etablissement
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtablissementDto updateEtablissement(EtablissementDto etablissementDto) throws Exception {


        saveEtablissementAuditData(etablissementDto, ACTION_TYPE.UPDATE);

        Etablissement etablissement = etablissementRepository.findById(etablissementDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Etablissement.class.getSimpleName(), etablissementDto.getId().toString()}));
        etablissement = etablissementDto.convertToEntity(etablissement, etablissementDto);
        etablissementRepository.saveAndFlush(etablissement);

        return etablissementDto;
    }

    /**
     * getEtablissementById.
     *
     * @param etablissementId
     * @return Etablissement
     * @throws Exception
     */
    public EtablissementDto getEtablissementById(Long etablissementId) throws Exception {

        Etablissement etablissement = etablissementRepository.findById(etablissementId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Etablissement.class.getSimpleName(), etablissementId.toString()}));

        return new EtablissementDto(etablissement, true, 0);

    }


    /**
     * findEtablissementsByCriteria.
     *
     * @param etablissementCriteria
     * @return List<Etablissement>
     * @throws Exception
     */
    public List<EtablissementDto> findEtablissementsByCriteria(EtablissementCriteria etablissementCriteria) throws Exception {

        Specification<Etablissement> specification = new EtablissementSpecification(etablissementCriteria);

        if (etablissementCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, etablissementCriteria.getMaxResults());
            return etablissementRepository.findAll(specification, pageable).getContent().stream().map(etablissement -> new EtablissementDto(etablissement)).collect(Collectors.toList());

        } else {
            return etablissementRepository.findAll(specification).stream().map(etablissement -> new EtablissementDto(etablissement)).collect(Collectors.toList());
        }

    }

    /**
     * findEtablissementByCriteria.
     *
     * @param etablissementCriteria
     * @return Etablissement
     * @throws Exception
     */

    public EtablissementDto findEtablissementByCriteria(EtablissementCriteria etablissementCriteria) throws Exception {

        Specification<Etablissement> specification = new EtablissementSpecification(etablissementCriteria);

        Etablissement etablissement = etablissementRepository.findOne(specification).orElse(null);

        EtablissementDto etablissementDto = null;
        if (etablissement != null) {
            etablissementDto = new EtablissementDto();
            return new EtablissementDto(etablissement, true);
        }

        return etablissementDto;
    }

    /**
     * paginatedListEtablissements.
     *
     * @param etablissementCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Etablissement>
     * @throws Exception
     */

    public List<EtablissementDto> paginatedListEtablissements(EtablissementCriteria etablissementCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<Etablissement> specification = new EtablissementSpecification(etablissementCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return etablissementRepository.findAll(specification, pageable).getContent().stream().map(etablissement -> new EtablissementDto(etablissement)).collect(Collectors.toList());
    }

    /**
     * getEtablissementDataSize.
     *
     * @param etablissementCriteria
     * @return int
     * @throws Exception
     */
    public int getEtablissementDataSize(EtablissementCriteria etablissementCriteria) throws Exception {

        Specification<Etablissement> specification = new EtablissementSpecification(etablissementCriteria, true);

        return ((Long) etablissementRepository.count(specification)).intValue();

    }

    /**
     * deleteEtablissement.
     *
     * @param etablissementList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteEtablissement(List<EtablissementDto> etablissementList) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (etablissementList != null)
            for (EtablissementDto etablissementDto : etablissementList) {
                Etablissement toBeDeleted = etablissementRepository.findById(etablissementDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Etablissement.class.getSimpleName(), etablissementDto.getId().toString()}));

                etablissementRepository.delete(toBeDeleted);

                HistEtablissement histEtablissement = new HistEtablissement();
                histEtablissement.setActionType(ACTION_TYPE.DELETE.name());
                histEtablissement.setObjectName("etablissement");
                histEtablissement.setObjectId(etablissementDto.getId());
                String etablissementValue = new ObjectMapper().writeValueAsString(etablissementDto);
                histEtablissement.setData(etablissementValue);
                histEtablissement.setUserId(currentUser.getId());
                histEtablissement.setUsername(currentUser.getUsername());
                histEtablissement.setDate(LocalDateTime.now());
                histEtablissementRepository.save(histEtablissement);
            }
    }


    private void saveEtablissementAuditData(EtablissementDto etablissement, ACTION_TYPE action) throws Exception {

        EtablissementDto oldEtablissement = getEtablissementById(etablissement.getId());
        if (Utils.compareObjectsDiff(etablissement, oldEtablissement)) {

            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HistEtablissement histEtablissement = new HistEtablissement();
            histEtablissement.setActionType(action.name());
            histEtablissement.setObjectName("etablissement");
            histEtablissement.setObjectId(etablissement.getId());
            histEtablissement.setUserId(currentUser.getId());
            histEtablissement.setUsername(currentUser.getUsername());
            String etablissementValue = new ObjectMapper().writeValueAsString(etablissement);
            histEtablissement.setData(etablissementValue);
            histEtablissement.setDate(LocalDateTime.now());
            histEtablissementRepository.save(histEtablissement);
        }
    }

    /**
     * getHistEtablissementById.
     *
     * @param histEtablissementId
     * @return AuditEntityDto
     * @throws Exception
     */
    public AuditEntityDto getHistEtablissementById(Long histEtablissementId) throws Exception {

        HistEtablissement histEtablissement = histEtablissementRepository.findById(histEtablissementId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{HistEtablissement.class.getSimpleName(), histEtablissementId.toString()}));

        return new AuditEntityDto(histEtablissement);

    }


    /**
     * paginatedListHistEtablissements.
     *
     * @param histEtablissementCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<HistEtablissement>
     * @throws Exception
     */

    public List<AuditEntityDto> paginatedListHistEtablissements(HistEtablissementCriteria histEtablissementCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<HistEtablissement> specification = new HistEtablissementSpecification(histEtablissementCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return histEtablissementRepository.findAll(specification, pageable).getContent().stream().map(etablissement -> new AuditEntityDto(etablissement)).collect(Collectors.toList());
    }

    /**
     * findEtablissementsHistByCriteria.
     *
     * @param HistEtablissementCriteria
     * @return List<HistEtablissement>
     * @throws Exception
     */
    public List<AuditEntityDto> findEtablissementsHistByCriteria(HistEtablissementCriteria histEtablissementCriteria) throws Exception {

        Specification<HistEtablissement> specification = new HistEtablissementSpecification(histEtablissementCriteria);

        if (histEtablissementCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, histEtablissementCriteria.getMaxResults());
            return histEtablissementRepository.findAll(specification, pageable).getContent().stream().map(etablissement -> new AuditEntityDto(etablissement)).collect(Collectors.toList());

        } else {
            return histEtablissementRepository.findAll(specification).stream().map(etablissement -> new AuditEntityDto(etablissement)).collect(Collectors.toList());
        }

    }

    /**
     * getHistEtablissementDataSize.
     *
     * @param histEtablissementCriteria
     * @return int
     * @throws Exception
     */
    public int getHistEtablissementDataSize(HistEtablissementCriteria histEtablissementCriteria) throws Exception {

        Specification<HistEtablissement> specification = new HistEtablissementSpecification(histEtablissementCriteria, true);

        return ((Long) histEtablissementRepository.count(specification)).intValue();

    }

}