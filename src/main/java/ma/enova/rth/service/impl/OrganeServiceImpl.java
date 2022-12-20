package ma.enova.rth.service.impl;

import ma.enova.rth.converter.OrganeConverter;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.dao.facade.core.IOrganeRepository;
import ma.enova.rth.dao.facade.history.IHistOrganeRepository;
import ma.enova.rth.dao.specifications.core.OrganeSpecification;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IOrganeService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service Iorgane
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "organeService")
public class OrganeServiceImpl extends ServiceImpl<Organe, OrganeDto, HistOrgane, OrganeCriteria, HistOrganeCriteria, IOrganeRepository, IHistOrganeRepository, OrganeConverter> implements IOrganeService {

    public OrganeServiceImpl(IOrganeRepository repository, IHistOrganeRepository historyRepository, OrganeConverter abstractConverter) {
        super(repository, historyRepository, abstractConverter, Organe.class,
                OrganeDto.class, HistOrgane.class,
                HistOrganeCriteria.class, OrganeSpecification.class);

    }

}