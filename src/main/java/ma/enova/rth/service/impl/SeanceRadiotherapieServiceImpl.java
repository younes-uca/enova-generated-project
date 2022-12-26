package ma.enova.rth.service.impl;

import ma.enova.rth.zynerator.service.ServiceImpl;
import ma.enova.rth.converter.SeanceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.ISeanceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistSeanceRadiotherapieRepository;
import ma.enova.rth.dao.specfication.core.SeanceRadiotherapieSpecification;
import ma.enova.rth.bean.core.SeanceRadiotherapie;
import ma.enova.rth.bean.historique.HistSeanceRadiotherapie;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service IseanceRadiotherapie
 *
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