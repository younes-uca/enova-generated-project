package ma.enova.rth.service.impl;

import ma.enova.rth.converter.PersonnelConverter;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dao.facade.core.IPersonnelRepository;
import ma.enova.rth.dao.facade.history.IHistPersonnelRepository;
import ma.enova.rth.dao.specifications.core.PersonnelSpecification;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IPersonnelService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service Ipersonnel
 *
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