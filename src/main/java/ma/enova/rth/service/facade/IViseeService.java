package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.bean.core.Visee;
import ma.enova.rth.dto.ViseeDto;

/**
 * Interface service visee
 *
 * @author JAF
 * @version 1.2
 */
public interface IViseeService extends IService<Visee, ViseeDto, ViseeCriteria, HistViseeCriteria> {
}