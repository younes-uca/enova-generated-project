package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.bean.core.Patient;
import ma.enova.rth.dto.PatientDto;

/**
 * Interface service patient
 *
 * @author JAF
 * @version 1.2
 */
public interface IPatientService extends IService<Patient, PatientDto, PatientCriteria, HistPatientCriteria> {
}