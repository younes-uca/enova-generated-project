package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.common.ddd.service.IService;

/**
 * Interface service seanceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */
public interface ISeanceRadiotherapieService extends IService<SeanceRadiotherapie, SeanceRadiotherapieDto, SeanceRadiotherapieCriteria, HistSeanceRadiotherapieCriteria> {
}