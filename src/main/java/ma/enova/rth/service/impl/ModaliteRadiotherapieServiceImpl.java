package ma.enova.rth.service.impl;

import ma.enova.rth.converter.ModaliteRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IModaliteRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistModaliteRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.ModaliteRadiotherapieSpecification;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service ImodaliteRadiotherapie
 *
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