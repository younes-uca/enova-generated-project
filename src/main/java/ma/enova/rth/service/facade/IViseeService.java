package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.service.core.IService;

import java.util.List;
/**
 * Interface service visee
 * @author JAF
 * @version 1.2
 */
public interface IViseeService extends IService<Visee, ViseeDto, ViseeCriteria, HistViseeCriteria> {
}