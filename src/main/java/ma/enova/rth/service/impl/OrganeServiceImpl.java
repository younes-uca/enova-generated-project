package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.OrganeConverter;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.dao.facade.core.IOrganeRepository;
import ma.enova.rth.dao.facade.core.IOrganeRepository;
import ma.enova.rth.dao.facade.history.IHistOrganeRepository;
import ma.enova.rth.dao.facade.history.IHistOrganeRepository;
import ma.enova.rth.dao.specifications.core.OrganeSpecification;
import ma.enova.rth.dao.specifications.core.OrganeSpecification;
import ma.enova.rth.dao.specifications.history.HistOrganeSpecification;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IOrganeService;
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
public class OrganeServiceImpl extends ServiceImpl<Organe, OrganeDto, HistOrgane, OrganeCriteria, HistOrganeCriteria, IOrganeRepository, IHistOrganeRepository, OrganeConverter> implements IOrganeService {

    public OrganeServiceImpl(IOrganeRepository repository, IHistOrganeRepository historyRepository, OrganeConverter abstractConverter) {
        super(repository, historyRepository, abstractConverter, Organe.class,
                OrganeDto.class, HistOrgane.class,
                HistOrganeCriteria.class, OrganeSpecification.class);

    }

}