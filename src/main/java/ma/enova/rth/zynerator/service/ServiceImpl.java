package ma.enova.rth.zynerator.service;

import ma.enova.rth.common.bean.*;
import ma.enova.rth.zynerator.audit.AuditBusinessObject;
import ma.enova.rth.zynerator.converter.AbstractConverter;
import ma.enova.rth.zynerator.criteria.BaseCriteria;
import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.dto.BaseDto;
import ma.enova.rth.zynerator.history.HistBusinessObject;
import ma.enova.rth.zynerator.history.HistCriteria;
import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.zynerator.repository.AbstractRepository;
import ma.enova.rth.zynerator.specification.AbstractHistorySpecification;
import ma.enova.rth.zynerator.specification.AbstractSpecification;
import ma.enova.rth.zynerator.enumeration.ACTION_TYPE;
import ma.enova.rth.zynerator.exception.EntityNotFoundException;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.zynerator.util.Utils;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServiceImpl<T extends AuditBusinessObject, DTO extends BaseDto, H extends HistBusinessObject, CRITERIA extends BaseCriteria, HC extends HistCriteria, REPO extends AbstractRepository<T, Long>, HISTREPO extends AbstractHistoryRepository<H, Long>, CONV extends AbstractConverter<T, DTO, H>> {

    protected AbstractSpecification<CRITERIA, T> specification;
    protected Class<? extends AbstractSpecification<CRITERIA, T>> specificationClass;

    protected HISTREPO historyRepository;
    protected Class<H> historyClass;
    protected Class<HC> historyCriteriaClass;
    protected Class<? extends AbstractHistorySpecification<HC, H>> historySPecificationClass;


    protected REPO repository;

    protected AbstractConverter<T, DTO, H> abstractConverter;

    protected Class<T> itemClass;
    protected Class<DTO> dtoClass;


    public ServiceImpl(REPO repository, HISTREPO historyRepository, CONV abstractConverter,
                       Class<T> itemClass, Class<DTO> dtoClass, Class<H> historyClass, Class<HC> historyCriteriaClass,
                       Class<? extends AbstractSpecification<CRITERIA, T>> specificationClass) {
        this.repository = repository;
        this.abstractConverter = abstractConverter;
        this.itemClass = itemClass;
        this.dtoClass = dtoClass;
        this.historyRepository = historyRepository;
        this.historyClass = historyClass;
        this.historyCriteriaClass = historyCriteriaClass;
        this.specificationClass = specificationClass;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DTO create(DTO dto) {
        T t = abstractConverter.toItem(dto);
        T saved = repository.save(t);
        dto.setId(saved.getId());
        return dto;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public DTO update(DTO dto) throws Exception {
        saveAuditData(dto, ACTION_TYPE.UPDATE);
        T loadedItem = repository.findById(dto.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{itemClass.getSimpleName(), dto.getId().toString()});
        }
        T item = abstractConverter.toItem(dto);
        repository.saveAndFlush(item);
        return dto;
    }

    public List<DTO> findMultipleByCriteria(CRITERIA criteria) {
        List<T> content = null;
        if (criteria != null) {
            addEtablissementConstraint(criteria);
            AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
            if (criteria.isPeagable()) {
                Pageable pageable = PageRequest.of(0, criteria.getMaxResults());
                content = repository.findAll(mySpecification, pageable).getContent();
            } else {
                content = repository.findAll(mySpecification);
            }
        } else {
            content = repository.findAll();
        }
        return abstractConverter.toDto(content);

    }

    private void addEtablissementConstraint(CRITERIA criteria) {
        //UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userInfo instanceof UtilisateurDetailsImpl) {
            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) userInfo;
            criteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        }
    }


    public List<DTO> findPaginatedByCriteria(CRITERIA criteria, int page, int pageSize, String order, String sortField) {
        addEtablissementConstraint(criteria);
        AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return abstractConverter.toDto(repository.findAll(mySpecification, pageable).getContent());
    }

    public int getDataSize(CRITERIA criteria) {
        addEtablissementConstraint(criteria);
        AbstractSpecification<CRITERIA, T> mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) repository.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void delete(List<DTO> list) {
        if (list != null) {
            for (DTO dto : list) {
                repository.deleteById(dto.getId()); // il fait find by id apres delete !!!
                constructAndSaveHistory(dto, ACTION_TYPE.DELETE);
            }
        }
    }


    public List<T> findAll() {
        return repository.findAll();
    }


    private AbstractSpecification<CRITERIA, T> constructSpecification(CRITERIA criteria) {
        AbstractSpecification<CRITERIA, T> mySpecification = RefelexivityUtil.constructObjectUsingDefaultConstr(specificationClass);
        mySpecification.setCriteria(criteria);
        return mySpecification;
    }

    //****************************** HISTORY


    // History methode
    public void saveAuditData(DTO dto, ACTION_TYPE action) throws Exception {
        DTO old = findById(dto.getId());
        if (Utils.compareObjectsDiff(dto, old)) {
            constructAndSaveHistory(dto, action);
        }
    }

    public AuditEntityDto findHistoryById(Long id) {
        H h = historyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{historyClass.getSimpleName(), id.toString()}));
        return (AuditEntityDto) abstractConverter.copyFromHistory(h);
    }


    public List<AuditEntityDto> findHistoryPaginatedByCriteria(HC historyCriteria, int page, int pageSize, String order, String sortField) {
        AbstractHistorySpecification<HC, H> mySpecification = constructSpecificationHistory(historyCriteria);
        order = StringUtil.isNotEmpty(order) ? order : "desc";
        sortField = StringUtil.isNotEmpty(sortField) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        List<H> content = historyRepository.findAll(mySpecification, pageable).getContent();
        return content.stream().map(h -> (AuditEntityDto) abstractConverter.copyFromHistory(h)).collect(Collectors.toList());
    }

    public List<AuditEntityDto> findHistoryByCriteria(HC historyCriteria) {
        AbstractHistorySpecification<HC, H> mySpecification = constructSpecificationHistory(historyCriteria);
        List<H> content = null;
        if (historyCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, historyCriteria.getMaxResults());
            content = historyRepository.findAll(mySpecification, pageable).getContent();
        } else {
            content = historyRepository.findAll(mySpecification);
        }
        return content.stream().map(h -> (AuditEntityDto) abstractConverter.copyFromHistory(h)).collect(Collectors.toList());
    }

    public int getHistoryDataSize(HC historyCriteria) {
        AbstractHistorySpecification<HC, H> mySpecification = constructSpecificationHistory(historyCriteria);
        mySpecification.setDistinct(true);
        return ((Long) historyRepository.count(mySpecification)).intValue();
    }

    public void constructAndSaveHistory(DTO dto, ACTION_TYPE action) {
        UtilisateurDetailsImpl currentUser = getCurrentUser();
        H history = RefelexivityUtil.constructObjectUsingDefaultConstr(historyClass);
        history.setActionType(action.name());
        history.setObjectName(itemClass.getSimpleName());
        history.setObjectId(dto.getId());
        history.setUserId(currentUser.getId());
        history.setUsername(currentUser.getUsername());
        String dtoAsJson = null;
        try {
            dtoAsJson = new ObjectMapper().writeValueAsString(dto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        history.setData(dtoAsJson);
        history.setDate(LocalDateTime.now());
        historyRepository.save(history);
    }


    private AbstractHistorySpecification<HC, H> constructSpecificationHistory(HC hc) {
        AbstractHistorySpecification<HC, H> mySpecification = RefelexivityUtil.constructObjectUsingDefaultConstr(historySPecificationClass);
        mySpecification.setCriteria(hc);
        return mySpecification;
    }


    public DTO findById(Long id) {
        Optional<T> item = repository.findById(id);
        return item.isPresent() ? abstractConverter.toDto(item.get()) : null;
    }


    public UtilisateurDetailsImpl getCurrentUser() {
        return (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
