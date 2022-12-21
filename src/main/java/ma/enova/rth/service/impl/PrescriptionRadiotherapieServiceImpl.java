package ma.enova.rth.service.impl;

import ma.enova.rth.converter.PrescriptionRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IPrescriptionRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistPrescriptionRadiotherapieRepository;
import ma.enova.rth.dao.specfication.core.PrescriptionRadiotherapieSpecification;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.common.ddd.service.ServiceImpl;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service IprescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "prescriptionRadiotherapieService")
public class PrescriptionRadiotherapieServiceImpl extends ServiceImpl<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, HistPrescriptionRadiotherapie, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria, IPrescriptionRadiotherapieRepository, IHistPrescriptionRadiotherapieRepository, PrescriptionRadiotherapieConverter> implements IPrescriptionRadiotherapieService {

    public PrescriptionRadiotherapieServiceImpl(IPrescriptionRadiotherapieRepository repository, IHistPrescriptionRadiotherapieRepository historyRepository, PrescriptionRadiotherapieConverter abstractConverter) {
        super(repository, historyRepository, abstractConverter, PrescriptionRadiotherapie.class,
                PrescriptionRadiotherapieDto.class, HistPrescriptionRadiotherapie.class,
                HistPrescriptionRadiotherapieCriteria.class, PrescriptionRadiotherapieSpecification.class);

    }

}