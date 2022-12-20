package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.service.core.IService;

/**
 * Interface service prescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */
public interface IPrescriptionRadiotherapieService extends IService<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria> {
}
