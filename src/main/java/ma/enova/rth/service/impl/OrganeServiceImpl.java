package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.dao.facade.core.IOrganeRepository;
import ma.enova.rth.dao.facade.history.IHistOrganeRepository;
import ma.enova.rth.dao.specifications.core.OrganeSpecification;
import ma.enova.rth.dao.specifications.history.HistOrganeSpecification;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IOrganeService;
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
 * Implementation du service Iorgane
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "organeService")
public class OrganeServiceImpl implements IOrganeService {

    @Autowired
    private IOrganeRepository organeRepository;

    @Autowired
    private IHistOrganeRepository histOrganeRepository;


    /**
     * createOrgane.
     *
     * @param organe
     * @return Organe
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrganeDto createOrgane(OrganeDto organeDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getEtablissement() != null)
            organeDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));

        Organe organe = new Organe();
        organe = organeDto.convertToEntity(organe, organeDto);
        Organe newOrgane = organeRepository.save(organe);
        organeDto.setId(newOrgane.getId());


        return organeDto;
    }

    /**
     * updateOrgane.
     *
     * @param organe
     * @return Organe
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public OrganeDto updateOrgane(OrganeDto organeDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        saveOrganeAuditData(organeDto, ACTION_TYPE.UPDATE);

        Organe organe = organeRepository.findById(organeDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Organe.class.getSimpleName(), organeDto.getId().toString()}));
        organe = organeDto.convertToEntity(organe, organeDto);
        organeRepository.saveAndFlush(organe);

        return organeDto;
    }

    /**
     * getOrganeById.
     *
     * @param organeId
     * @return Organe
     * @throws Exception
     */
    public OrganeDto getOrganeById(Long organeId) throws Exception {

        Organe organe = organeRepository.findById(organeId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Organe.class.getSimpleName(), organeId.toString()}));

        return new OrganeDto(organe, true, 0);

    }


    /**
     * findOrganesByCriteria.
     *
     * @param organeCriteria
     * @return List<Organe>
     * @throws Exception
     */
    public List<OrganeDto> findOrganesByCriteria(OrganeCriteria organeCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        organeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Organe> specification = new OrganeSpecification(organeCriteria);

        if (organeCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, organeCriteria.getMaxResults());
            return organeRepository.findAll(specification, pageable).getContent().stream().map(organe -> new OrganeDto(organe)).collect(Collectors.toList());

        } else {
            return organeRepository.findAll(specification).stream().map(organe -> new OrganeDto(organe)).collect(Collectors.toList());
        }

    }

    /**
     * findOrganeByCriteria.
     *
     * @param organeCriteria
     * @return Organe
     * @throws Exception
     */

    public OrganeDto findOrganeByCriteria(OrganeCriteria organeCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        organeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Organe> specification = new OrganeSpecification(organeCriteria);

        Organe organe = organeRepository.findOne(specification).orElse(null);

        OrganeDto organeDto = null;
        if (organe != null) {
            organeDto = new OrganeDto();
            return new OrganeDto(organe, true);
        }

        return organeDto;
    }

    /**
     * paginatedListOrganes.
     *
     * @param organeCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Organe>
     * @throws Exception
     */

    public List<OrganeDto> paginatedListOrganes(OrganeCriteria organeCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        organeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Organe> specification = new OrganeSpecification(organeCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return organeRepository.findAll(specification, pageable).getContent().stream().map(organe -> new OrganeDto(organe)).collect(Collectors.toList());
    }

    /**
     * getOrganeDataSize.
     *
     * @param organeCriteria
     * @return int
     * @throws Exception
     */
    public int getOrganeDataSize(OrganeCriteria organeCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        organeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Organe> specification = new OrganeSpecification(organeCriteria, true);

        return ((Long) organeRepository.count(specification)).intValue();

    }

    /**
     * deleteOrgane.
     *
     * @param organeList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteOrgane(List<OrganeDto> organeList) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (organeList != null)
            for (OrganeDto organeDto : organeList) {
                Organe toBeDeleted = organeRepository.findById(organeDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Organe.class.getSimpleName(), organeDto.getId().toString()}));

                organeRepository.delete(toBeDeleted);

                HistOrgane histOrgane = new HistOrgane();
                histOrgane.setActionType(ACTION_TYPE.DELETE.name());
                histOrgane.setObjectName("organe");
                histOrgane.setObjectId(organeDto.getId());
                String organeValue = new ObjectMapper().writeValueAsString(organeDto);
                histOrgane.setData(organeValue);
                histOrgane.setUserId(currentUser.getId());
                histOrgane.setUsername(currentUser.getUsername());
                histOrgane.setDate(LocalDateTime.now());
                histOrganeRepository.save(histOrgane);
            }
    }


    private void saveOrganeAuditData(OrganeDto organe, ACTION_TYPE action) throws Exception {

        OrganeDto oldOrgane = getOrganeById(organe.getId());
        if (Utils.compareObjectsDiff(organe, oldOrgane)) {

            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HistOrgane histOrgane = new HistOrgane();
            histOrgane.setActionType(action.name());
            histOrgane.setObjectName("organe");
            histOrgane.setObjectId(organe.getId());
            histOrgane.setUserId(currentUser.getId());
            histOrgane.setUsername(currentUser.getUsername());
            String organeValue = new ObjectMapper().writeValueAsString(organe);
            histOrgane.setData(organeValue);
            histOrgane.setDate(LocalDateTime.now());
            histOrganeRepository.save(histOrgane);
        }
    }

    /**
     * getHistOrganeById.
     *
     * @param histOrganeId
     * @return AuditEntityDto
     * @throws Exception
     */
    public AuditEntityDto getHistOrganeById(Long histOrganeId) throws Exception {

        HistOrgane histOrgane = histOrganeRepository.findById(histOrganeId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{HistOrgane.class.getSimpleName(), histOrganeId.toString()}));

        return new AuditEntityDto(histOrgane);

    }


    /**
     * paginatedListHistOrganes.
     *
     * @param histOrganeCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<HistOrgane>
     * @throws Exception
     */

    public List<AuditEntityDto> paginatedListHistOrganes(HistOrganeCriteria histOrganeCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<HistOrgane> specification = new HistOrganeSpecification(histOrganeCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return histOrganeRepository.findAll(specification, pageable).getContent().stream().map(organe -> new AuditEntityDto(organe)).collect(Collectors.toList());
    }

    /**
     * findOrganesHistByCriteria.
     *
     * @param HistOrganeCriteria
     * @return List<HistOrgane>
     * @throws Exception
     */
    public List<AuditEntityDto> findOrganesHistByCriteria(HistOrganeCriteria histOrganeCriteria) throws Exception {

        Specification<HistOrgane> specification = new HistOrganeSpecification(histOrganeCriteria);

        if (histOrganeCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, histOrganeCriteria.getMaxResults());
            return histOrganeRepository.findAll(specification, pageable).getContent().stream().map(organe -> new AuditEntityDto(organe)).collect(Collectors.toList());

        } else {
            return histOrganeRepository.findAll(specification).stream().map(organe -> new AuditEntityDto(organe)).collect(Collectors.toList());
        }

    }

    /**
     * getHistOrganeDataSize.
     *
     * @param histOrganeCriteria
     * @return int
     * @throws Exception
     */
    public int getHistOrganeDataSize(HistOrganeCriteria histOrganeCriteria) throws Exception {

        Specification<HistOrgane> specification = new HistOrganeSpecification(histOrganeCriteria, true);

        return ((Long) histOrganeRepository.count(specification)).intValue();

    }

    @Override
    public List<Organe> findAll() {
        return organeRepository.findAll();
    }

}