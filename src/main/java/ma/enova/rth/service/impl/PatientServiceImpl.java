package ma.enova.rth.service.impl;

import ma.enova.rth.converter.PatientConverter;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dao.facade.core.IPatientRepository;
import ma.enova.rth.dao.facade.history.IHistPatientRepository;
import ma.enova.rth.dao.specfication.core.PatientSpecification;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.common.ddd.service.ServiceImpl;
import ma.enova.rth.service.facade.IPatientService;
import org.springframework.stereotype.Service;

/**
 * Implementation du service Ipatient
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "patientService")
public class PatientServiceImpl extends ServiceImpl<Patient, PatientDto, HistPatient, PatientCriteria, HistPatientCriteria, IPatientRepository, IHistPatientRepository, PatientConverter> implements IPatientService {

    public PatientServiceImpl(IPatientRepository repository, IHistPatientRepository historyRepository, PatientConverter abstractConverter) {
        super(repository, historyRepository, abstractConverter, Patient.class,
                PatientDto.class, HistPatient.class,
                HistPatientCriteria.class, PatientSpecification.class);

    }

}