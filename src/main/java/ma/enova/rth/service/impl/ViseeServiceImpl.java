package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.ViseeConverter;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.facade.core.IViseeRepository;
import ma.enova.rth.dao.facade.core.IViseeRepository;
import ma.enova.rth.dao.facade.history.IHistViseeRepository;
import ma.enova.rth.dao.facade.history.IHistViseeRepository;
import ma.enova.rth.dao.specifications.core.ViseeSpecification;
import ma.enova.rth.dao.specifications.core.ViseeSpecification;
import ma.enova.rth.dao.specifications.history.HistViseeSpecification;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.domain.historique.HistVisee;
import ma.enova.rth.domain.historique.HistVisee;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IViseeService;
import ma.enova.rth.service.facade.IViseeService;
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
 * Implementation du service Ivisee
 * @author JAF
 * @version 1.2
 */

@Service(value = "viseeService")
public class ViseeServiceImpl extends ServiceImpl<Visee, ViseeDto, HistVisee, ViseeCriteria, HistViseeCriteria, IViseeRepository, IHistViseeRepository, ViseeConverter> implements IViseeService {

	public ViseeServiceImpl(IViseeRepository repository, IHistViseeRepository historyRepository, ViseeConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, Visee.class,
				ViseeDto.class, HistVisee.class,
				HistViseeCriteria.class, ViseeSpecification.class);

	}

}