package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dao.facade.core.IPatientRepository;
import ma.enova.rth.dao.facade.history.IHistPatientRepository;
import ma.enova.rth.dao.specifications.core.PatientSpecification;
import ma.enova.rth.dao.specifications.history.HistPatientSpecification;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
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
public class PatientServiceImpl implements IPatientService {

	@Autowired
	private IPatientRepository patientRepository;
	
	@Autowired
	private IHistPatientRepository histPatientRepository;



	/**
	 * createPatient.
	 * 
	 * @param patient
	 * @return Patient
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public PatientDto createPatient(PatientDto patientDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			patientDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		Patient patient = new Patient();
		patient = patientDto.convertToEntity(patient, patientDto);
		Patient newPatient = patientRepository.save(patient);
		patientDto.setId(newPatient.getId());		



			
		return patientDto;
	}
	
	/**
	 * updatePatient.
	 * 
	 * @param patient
	 * @return Patient
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public PatientDto updatePatient(PatientDto patientDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		savePatientAuditData(patientDto, ACTION_TYPE.UPDATE);

		Patient patient = patientRepository.findById(patientDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Patient.class.getSimpleName(), patientDto.getId().toString() }));
		patient = patientDto.convertToEntity(patient, patientDto);
		patientRepository.saveAndFlush(patient);
		
		return patientDto;
	}
	
	/**
	 * getPatientById.
	 * 
	 * @param patientId
	 * @return Patient
	 * @throws Exception
	 */
	public PatientDto getPatientById(Long patientId) throws Exception {

		Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Patient.class.getSimpleName(), patientId.toString() }));

		return  new PatientDto(patient, true, 0);

	}
	

	/**
	 * findPatientsByCriteria.
	 * 
	 * @param patientCriteria
	 * @return List<Patient>
	 * @throws Exception
	 */	
	public List<PatientDto> findPatientsByCriteria(PatientCriteria patientCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		patientCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Patient> specification = new PatientSpecification(patientCriteria);
		
		if (patientCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, patientCriteria.getMaxResults());
			return patientRepository.findAll(specification, pageable).getContent().stream().map(patient -> new PatientDto(patient)).collect(Collectors.toList());

		} else {
			return patientRepository.findAll(specification).stream().map(patient -> new PatientDto(patient)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findPatientByCriteria.
	 * 
	 * @param patientCriteria
	 * @return Patient 
	 * @throws Exception
	 */
	
	public PatientDto findPatientByCriteria(PatientCriteria  patientCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		patientCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Patient> specification = new PatientSpecification(patientCriteria);
		
		Patient patient = patientRepository.findOne(specification).orElse(null);
		
		PatientDto patientDto = null;
		if (patient != null) {
			patientDto = new PatientDto();
			return new PatientDto(patient, true);
		}

		return patientDto;
	}

	/**
	 * paginatedListPatients.
	 * 
	 * @param patientCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Patient>
	 * @throws Exception
	 */
	 
	public List<PatientDto> paginatedListPatients(PatientCriteria patientCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		patientCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Patient> specification = new PatientSpecification(patientCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return patientRepository.findAll(specification, pageable).getContent().stream().map(patient -> new PatientDto(patient)).collect(Collectors.toList());
	}
	
	/**
	 * getPatientDataSize.
	 * 
	 * @param patientCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getPatientDataSize(PatientCriteria patientCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		patientCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Patient> specification = new PatientSpecification(patientCriteria, true);
		
		return ((Long)  patientRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deletePatient.
	 * 
	 * @param patientList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deletePatient(List<PatientDto> patientList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (patientList != null)
			for (PatientDto patientDto : patientList) {
					Patient toBeDeleted = patientRepository.findById(patientDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Patient.class.getSimpleName(), patientDto.getId().toString() }));

					patientRepository.delete(toBeDeleted);

					HistPatient histPatient = new HistPatient();
					histPatient.setActionType(ACTION_TYPE.DELETE.name());
					histPatient.setObjectName("patient");
					histPatient.setObjectId(patientDto.getId());
					String patientValue =  new ObjectMapper().writeValueAsString(patientDto);
					histPatient.setData(patientValue);
					histPatient.setUserId(currentUser.getId());
					histPatient.setUsername(currentUser.getUsername());
					histPatient.setDate(LocalDateTime.now());
					histPatientRepository.save(histPatient);					
			}
	}
	
	

	
	private void savePatientAuditData(PatientDto patient, ACTION_TYPE action) throws Exception {

		PatientDto oldPatient = getPatientById(patient.getId());
		if (Utils.compareObjectsDiff(patient, oldPatient)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistPatient histPatient = new HistPatient();
			histPatient.setActionType(action.name());
			histPatient.setObjectName("patient");
			histPatient.setObjectId(patient.getId());
			histPatient.setUserId(currentUser.getId());
			histPatient.setUsername(currentUser.getUsername());
			String patientValue =  new ObjectMapper().writeValueAsString(patient);
			histPatient.setData(patientValue);
			histPatient.setDate(LocalDateTime.now());
			histPatientRepository.save(histPatient);
		}
	}	
	
	/**
	 * getHistPatientById.
	 * 
	 * @param histPatientId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistPatientById(Long histPatientId) throws Exception {

		HistPatient histPatient = histPatientRepository.findById(histPatientId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistPatient.class.getSimpleName(), histPatientId.toString() }));

			return  new AuditEntityDto(histPatient);

	}
	

	/**
	 * paginatedListHistPatients.
	 * 
	 * @param histPatientCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistPatient>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistPatients(HistPatientCriteria histPatientCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistPatient> specification = new HistPatientSpecification(histPatientCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histPatientRepository.findAll(specification, pageable).getContent().stream().map(patient -> new AuditEntityDto(patient)).collect(Collectors.toList());
	}

	/**
	 * findPatientsHistByCriteria.
	 * 
	 * @param HistPatientCriteria
	 * @return List<HistPatient>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findPatientsHistByCriteria(HistPatientCriteria histPatientCriteria) throws Exception {
					 
		Specification<HistPatient> specification = new HistPatientSpecification(histPatientCriteria);
		
		if (histPatientCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histPatientCriteria.getMaxResults());
			return histPatientRepository.findAll(specification, pageable).getContent().stream().map(patient -> new AuditEntityDto(patient)).collect(Collectors.toList());

		} else {
			return histPatientRepository.findAll(specification).stream().map(patient -> new AuditEntityDto(patient)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistPatientDataSize.
	 * 
	 * @param histPatientCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistPatientDataSize(HistPatientCriteria histPatientCriteria) throws Exception {
	
		Specification<HistPatient> specification = new HistPatientSpecification(histPatientCriteria, true);
		
		return ((Long)  histPatientRepository.count(specification)).intValue();
		
	}	

}