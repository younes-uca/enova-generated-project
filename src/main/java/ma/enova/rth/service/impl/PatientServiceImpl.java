package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.converter.PatientConverter;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dao.facade.core.IPatientRepository;
import ma.enova.rth.dao.facade.core.IPatientRepository;
import ma.enova.rth.dao.facade.history.IHistPatientRepository;
import ma.enova.rth.dao.facade.history.IHistPatientRepository;
import ma.enova.rth.dao.specifications.core.PatientSpecification;
import ma.enova.rth.dao.specifications.core.PatientSpecification;
import ma.enova.rth.dao.specifications.history.HistPatientSpecification;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.core.ServiceImpl;
import ma.enova.rth.service.facade.IPatientService;
import ma.enova.rth.service.facade.IPatientService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementation du service Ipatient
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