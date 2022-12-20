package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.service.core.IService;

import java.util.List;
/**
 * Interface service patient
 * @author JAF
 * @version 1.2
 */
public interface IPatientService extends IService<Patient, PatientDto, PatientCriteria, HistPatientCriteria> {
}