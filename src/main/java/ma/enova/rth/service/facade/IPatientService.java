package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dto.PatientDto;

import java.util.List;
/**
 * Interface service patient
 * @author JAF
 * @version 1.2
 */
public interface IPatientService
{


	/**
	 * createPatient.
	 * 
	 * @param patient
	 * @return Patient
	 * @throws Exception
	 */
	public PatientDto createPatient(PatientDto patient) throws Exception;

	/**
	 * updatePatient.
	 * 
	 * @param patient
	 * @return Patient
	 * @throws Exception
	 */
	public PatientDto updatePatient(PatientDto patient) throws Exception;

	
	/**
	 * getPatientById.
	 * 
	 * @param patientId
	 * @return Patient
	 * @throws Exception
	 */	
	public PatientDto getPatientById(Long patientId) throws Exception;
	
	/**
	 * findPatientsByCriteria.
	 * 
	 * @param patientCriteria
	 * @return List<Patient>
	 * @throws Exception
	 */	
	public List<PatientDto> findPatientsByCriteria(PatientCriteria patientCriteria) throws Exception;
	
	/**
	 * findPatientByCriteria.
	 * 
	 * @param patientCriteria
	 * @return Patient 
	 * @throws Exception
	 */
	
	public PatientDto findPatientByCriteria(PatientCriteria  patientCriteria) throws Exception;
	
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
	public List<PatientDto> paginatedListPatients(PatientCriteria patientCriteria,int page,int pageSize, String order, String sortField) throws Exception;
	
	/**
	 * getPatientDataSize.
	 * 
	 * @param patientCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getPatientDataSize(PatientCriteria patientCriteria)  throws Exception;
	
	/**
	 * deletePatient.
	 * 
	 * @param patientList
	 * @throws Exception
	 */
	public void deletePatient(List<PatientDto> patientList) throws Exception;
	

	/**
	 * getHistPatientById.
	 * 
	 * @param histPatientId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistPatientById(Long histPatientId) throws Exception;
	
	/**
	 * paginatedListHistPatients.
	 * 
	 * @param histPatientCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistPatients(HistPatientCriteria histPatientCriteria,int page,int pageSize, String order, String sortField) throws Exception;

	/**
	 * findPatientHistsByCriteria.
	 * 
	 * @param HistPatientCriteria
	 * @return List<AuditEntityDto>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findPatientsHistByCriteria(HistPatientCriteria histPatientCriteria) throws Exception;
	
	/**
	 * getHistPatientDataSize.
	 * 
	 * @param histPatientCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistPatientDataSize(HistPatientCriteria histPatientCriteria) throws Exception;	
		

}
