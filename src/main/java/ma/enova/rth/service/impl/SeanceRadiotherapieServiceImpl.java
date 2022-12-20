package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.SeanceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.ISeanceRadiotherapieRepository;
import ma.enova.rth.dao.facade.core.ISeanceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistSeanceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistSeanceRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.SeanceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.core.SeanceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistSeanceRadiotherapieSpecification;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
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
 * Implementation du service IseanceRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "seanceRadiotherapieService")
public class SeanceRadiotherapieServiceImpl extends ServiceImpl<SeanceRadiotherapie, SeanceRadiotherapieDto, HistSeanceRadiotherapie, SeanceRadiotherapieCriteria, HistSeanceRadiotherapieCriteria, ISeanceRadiotherapieRepository, IHistSeanceRadiotherapieRepository, SeanceRadiotherapieConverter> implements ISeanceRadiotherapieService {

	public SeanceRadiotherapieServiceImpl(ISeanceRadiotherapieRepository repository, IHistSeanceRadiotherapieRepository historyRepository, SeanceRadiotherapieConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, SeanceRadiotherapie.class,
				SeanceRadiotherapieDto.class, HistSeanceRadiotherapie.class,
				HistSeanceRadiotherapieCriteria.class, SeanceRadiotherapieSpecification.class);

	}

}