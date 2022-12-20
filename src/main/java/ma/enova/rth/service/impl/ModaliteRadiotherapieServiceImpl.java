package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.ModaliteRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IModaliteRadiotherapieRepository;
import ma.enova.rth.dao.facade.core.IModaliteRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistModaliteRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistModaliteRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.ModaliteRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.core.ModaliteRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistModaliteRadiotherapieSpecification;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
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
 * Implementation du service ImodaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "modaliteRadiotherapieService")
public class ModaliteRadiotherapieServiceImpl extends ServiceImpl<ModaliteRadiotherapie, ModaliteRadiotherapieDto, HistModaliteRadiotherapie, ModaliteRadiotherapieCriteria, HistModaliteRadiotherapieCriteria, IModaliteRadiotherapieRepository, IHistModaliteRadiotherapieRepository, ModaliteRadiotherapieConverter> implements IModaliteRadiotherapieService {

	public ModaliteRadiotherapieServiceImpl(IModaliteRadiotherapieRepository repository, IHistModaliteRadiotherapieRepository historyRepository, ModaliteRadiotherapieConverter abstractConverter) {
		super(repository, historyRepository, abstractConverter, ModaliteRadiotherapie.class,
				ModaliteRadiotherapieDto.class, HistModaliteRadiotherapie.class,
				HistModaliteRadiotherapieCriteria.class, ModaliteRadiotherapieSpecification.class);

	}

}