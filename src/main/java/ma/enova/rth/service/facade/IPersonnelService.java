package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.bean.core.Personnel;
import ma.enova.rth.dto.PersonnelDto;

/**
 * Interface service personnel
 *
 * @author JAF
 * @version 1.2
 */
public interface IPersonnelService extends IService<Personnel, PersonnelDto, PersonnelCriteria, HistPersonnelCriteria> {
}
