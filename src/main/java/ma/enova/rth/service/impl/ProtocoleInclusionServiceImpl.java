package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.ProtocoleInclusionConverter;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dao.facade.core.IProtocoleInclusionRepository;
import ma.enova.rth.dao.facade.core.IProtocoleInclusionRepository;
import ma.enova.rth.dao.facade.history.IHistProtocoleInclusionRepository;
import ma.enova.rth.dao.facade.history.IHistProtocoleInclusionRepository;
import ma.enova.rth.dao.specifications.core.ProtocoleInclusionSpecification;
import ma.enova.rth.dao.specifications.core.ProtocoleInclusionSpecification;
import ma.enova.rth.dao.specifications.history.HistProtocoleInclusionSpecification;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.domain.historique.HistProtocoleInclusion;
import ma.enova.rth.domain.historique.HistProtocoleInclusion;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
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
 * Implementation du service IprotocoleInclusion
 * @author JAF
 * @version 1.2
 */

@Service(value = "protocoleInclusionService")
public class ProtocoleInclusionServiceImpl extends ServiceImpl<ProtocoleInclusion, ProtocoleInclusionDto, HistProtocoleInclusion, ProtocoleInclusionCriteria, HistProtocoleInclusionCriteria, IProtocoleInclusionRepository, IHistProtocoleInclusionRepository, ProtocoleInclusionConverter> implements IProtocoleInclusionService {

	public ProtocoleInclusionServiceImpl(IProtocoleInclusionRepository repository, IHistProtocoleInclusionRepository historyRepository, ProtocoleInclusionConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, ProtocoleInclusion.class,
				ProtocoleInclusionDto.class, HistProtocoleInclusion.class,
				HistProtocoleInclusionCriteria.class, ProtocoleInclusionSpecification.class);

	}

}