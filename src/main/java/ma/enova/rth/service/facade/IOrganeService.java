package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.service.core.IService;

/**
 * Interface service organe
 *
 * @author JAF
 * @version 1.2
 */
public interface IOrganeService extends IService<Organe, OrganeDto, OrganeCriteria, HistOrganeCriteria> {
}