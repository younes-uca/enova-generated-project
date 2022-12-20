package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.FrequenceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.facade.core.IFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.FrequenceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.core.FrequenceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistFrequenceRadiotherapieSpecification;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
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
 * Implementation du service IfrequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "frequenceRadiotherapieService")
public class FrequenceRadiotherapieServiceImpl extends ServiceImpl<FrequenceRadiotherapie, FrequenceRadiotherapieDto, HistFrequenceRadiotherapie, FrequenceRadiotherapieCriteria, HistFrequenceRadiotherapieCriteria, IFrequenceRadiotherapieRepository, IHistFrequenceRadiotherapieRepository, FrequenceRadiotherapieConverter> implements IFrequenceRadiotherapieService {

	public FrequenceRadiotherapieServiceImpl(IFrequenceRadiotherapieRepository repository, IHistFrequenceRadiotherapieRepository historyRepository, FrequenceRadiotherapieConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, FrequenceRadiotherapie.class,
				FrequenceRadiotherapieDto.class, HistFrequenceRadiotherapie.class,
				HistFrequenceRadiotherapieCriteria.class, FrequenceRadiotherapieSpecification.class);

	}

}