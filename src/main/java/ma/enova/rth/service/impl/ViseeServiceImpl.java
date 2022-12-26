package ma.enova.rth.service.impl;

import ma.enova.rth.zynerator.service.ServiceImpl;
import ma.enova.rth.converter.ViseeConverter;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.facade.core.IViseeRepository;
import ma.enova.rth.dao.facade.history.IHistViseeRepository;
import ma.enova.rth.dao.specfication.core.ViseeSpecification;
import ma.enova.rth.bean.core.Visee;
import ma.enova.rth.bean.historique.HistVisee;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.service.facade.IViseeService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service Ivisee
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "viseeService")
public class ViseeServiceImpl extends ServiceImpl<Visee, ViseeDto, HistVisee, ViseeCriteria, HistViseeCriteria, IViseeRepository, IHistViseeRepository, ViseeConverter> implements IViseeService {

    public ViseeServiceImpl(IViseeRepository repository, IHistViseeRepository historyRepository, ViseeConverter abstractConverter) {
        super(repository, historyRepository, abstractConverter, Visee.class,
                ViseeDto.class, HistVisee.class,
                HistViseeCriteria.class, ViseeSpecification.class);

    }

}