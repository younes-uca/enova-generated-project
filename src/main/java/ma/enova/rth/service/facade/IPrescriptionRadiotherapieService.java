package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.bean.core.PrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;

/**
 * Interface service prescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */
public interface IPrescriptionRadiotherapieService extends IService<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto,
        PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria> {
}
