package ma.enova.rth.service.impl;

import ma.enova.rth.zynerator.service.ServiceImpl;
import ma.enova.rth.converter.ProtocoleInclusionConverter;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dao.facade.core.IProtocoleInclusionRepository;
import ma.enova.rth.dao.facade.history.IHistProtocoleInclusionRepository;
import ma.enova.rth.dao.specfication.core.ProtocoleInclusionSpecification;
import ma.enova.rth.bean.core.ProtocoleInclusion;
import ma.enova.rth.bean.historique.HistProtocoleInclusion;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service IprotocoleInclusion
 *
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