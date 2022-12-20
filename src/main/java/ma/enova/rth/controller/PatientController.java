package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.service.facade.IPatientService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager controller : Patient
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class PatientController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IPatientService patientService;

	@GetMapping("/api/patient/{id}")
	@PreAuthorize("hasRole('ROLE_READ_PATIENT')")
	public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		PatientDto patient = patientService.getPatientById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			patient = new PatientDto().mappedCustomDto(patient, includes, excludes);

		return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);

	}
	
	@PostMapping("/api/patient")
	@PreAuthorize("hasRole('ROLE_CREATE_PATIENT')")
	public ResponseEntity<Long> addPatient(@RequestBody PatientDto patient) throws Exception {

		patient = patientService.createPatient(patient);
		
		return new ResponseEntity<Long>(patient.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/patient")	
	@PreAuthorize("hasRole('ROLE_UPDATE_PATIENT')")
	public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patient) throws Exception {

		if (patient.getId() == null)
			return new ResponseEntity<PatientDto>(HttpStatus.CONFLICT);

		patient = patientService.updatePatient(patient);

		return new ResponseEntity<PatientDto>(patient, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/patient/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_PATIENT')")
	public ResponseEntity<Void> deletePatient(@RequestBody List<PatientDto> patientList) throws Exception {

		if (patientList == null || patientList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		patientService.deletePatient(patientList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/patient/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<PatientDto>> getPatientsByCriteria(@RequestBody PatientCriteria patientCriteria) throws Exception {

		List<PatientDto> list = patientService.findPatientsByCriteria(patientCriteria);

		if (StringUtil.isNoEmpty(patientCriteria.getIncludes()) || StringUtil.isNoEmpty(patientCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(patient -> new PatientDto().mappedCustomDto(patient, patientCriteria.getIncludes(), patientCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<PatientDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PatientDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/patient/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_PATIENT')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListPatients(@RequestBody PatientCriteria patientCriteria) throws Exception {

		List<PatientDto> list = patientService.paginatedListPatients(patientCriteria,patientCriteria.getPage(),patientCriteria.getMaxResults(), patientCriteria.getSortOrder(), patientCriteria.getSortField());

		if (StringUtil.isNoEmpty(patientCriteria.getIncludes()) || StringUtil.isNoEmpty(patientCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(patient -> new PatientDto().mappedCustomDto(patient, patientCriteria.getIncludes(), patientCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = patientService.getPatientDataSize(patientCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/patient/exportPatients")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportPatients(@RequestBody PatientCriteria patientCriteria) throws Exception {

		if (patientCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		patientCriteria.setMaxResults(null);
		List<PatientDto> list = patientService.findPatientsByCriteria(patientCriteria);
		patientCriteria.getExportModel().setList(list);
		return getExportedFileResource(patientCriteria.getExportModel());
	
	}

	@PostMapping("/api/patient/getPatientsDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getPatientDataSize(@RequestBody PatientCriteria patientCriteria) throws Exception {

		int count = patientService.getPatientDataSize(patientCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/patient/histPatient/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_PATIENT')")
	public ResponseEntity<AuditEntityDto> getHistPatientById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histPatient = patientService.getHistPatientById(id);

		return new ResponseEntity<AuditEntityDto>(histPatient, HttpStatus.OK);

	}
	
	@PostMapping("/api/patient/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_PATIENT')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistPatients(@RequestBody HistPatientCriteria histPatientCriteria) throws Exception {

		List<AuditEntityDto> list = patientService.paginatedListHistPatients(histPatientCriteria,histPatientCriteria.getPage(), histPatientCriteria.getMaxResults(), histPatientCriteria.getSortOrder(), histPatientCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = patientService.getHistPatientDataSize(histPatientCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/patient/exportPatientsHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportPatientsHist(@RequestBody HistPatientCriteria histPatientCriteria) throws Exception {

		if (histPatientCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histPatientCriteria.setMaxResults(null);
		List<AuditEntityDto> list = patientService.findPatientsHistByCriteria(histPatientCriteria);
		histPatientCriteria.getExportModel().setList(list);
		return getExportedFileResource(histPatientCriteria.getExportModel());
	
	}

	@PostMapping("/api/patient/getHistPatientsDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistPatientDataSize(@RequestBody HistPatientCriteria histPatientCriteria) throws Exception {

		int count = patientService.getHistPatientDataSize(histPatientCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}