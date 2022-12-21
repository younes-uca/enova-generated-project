package ma.enova.rth.service.impl;

import ma.enova.rth.converter.FrequenceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.specfication.core.FrequenceRadiotherapieSpecification;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.common.ddd.service.ServiceImpl;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service IfrequenceRadiotherapie
 *
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