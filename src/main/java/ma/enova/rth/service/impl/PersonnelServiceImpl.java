package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.PersonnelConverter;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dao.facade.core.IPersonnelRepository;
import ma.enova.rth.dao.facade.core.IPersonnelRepository;
import ma.enova.rth.dao.facade.history.IHistPersonnelRepository;
import ma.enova.rth.dao.facade.history.IHistPersonnelRepository;
import ma.enova.rth.dao.specifications.core.PersonnelSpecification;
import ma.enova.rth.dao.specifications.core.PersonnelSpecification;
import ma.enova.rth.dao.specifications.history.HistPersonnelSpecification;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IPersonnelService;
import ma.enova.rth.service.facade.IPersonnelService;
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
 * Implementation du service Ipersonnel
 * @author JAF
 * @version 1.2
 */

@Service(value = "personnelService")
public class PersonnelServiceImpl extends ServiceImpl<Personnel, PersonnelDto, HistPersonnel, PersonnelCriteria, HistPersonnelCriteria, IPersonnelRepository, IHistPersonnelRepository, PersonnelConverter> implements IPersonnelService {

	public PersonnelServiceImpl(IPersonnelRepository repository, IHistPersonnelRepository historyRepository, PersonnelConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, Personnel.class,
				PersonnelDto.class, HistPersonnel.class,
				HistPersonnelCriteria.class, PersonnelSpecification.class);

	}

}