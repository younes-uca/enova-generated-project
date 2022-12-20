package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.service.core.IService;

import java.util.List;

/**
 * Interface service personnel
 *
 * @author JAF
 * @version 1.2
 */
public interface IPersonnelService extends IService<Personnel, PersonnelDto, PersonnelCriteria, HistPersonnelCriteria> {
}
