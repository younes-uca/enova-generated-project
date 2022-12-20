package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.BusinessDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
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
 * Manager controller : SeanceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class SeanceRadiotherapieController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private ISeanceRadiotherapieService seanceRadiotherapieService;
	@Autowired	
	private IPrescriptionRadiotherapieService prescriptionRadiotherapieService;

	@GetMapping("/api/seanceRadiotherapie/{id}")
	@PreAuthorize("hasRole('ROLE_READ_SEANCERADIOTHERAPIE')")
	public ResponseEntity<SeanceRadiotherapieDto> getSeanceRadiotherapieById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		SeanceRadiotherapieDto seanceRadiotherapie = seanceRadiotherapieService.getSeanceRadiotherapieById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			seanceRadiotherapie = new SeanceRadiotherapieDto().mappedCustomDto(seanceRadiotherapie, includes, excludes);

		return new ResponseEntity<SeanceRadiotherapieDto>(seanceRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/seanceRadiotherapie")
	@PreAuthorize("hasRole('ROLE_CREATE_SEANCERADIOTHERAPIE')")
	public ResponseEntity<Long> addSeanceRadiotherapie(@RequestBody SeanceRadiotherapieDto seanceRadiotherapie) throws Exception {

		seanceRadiotherapie = seanceRadiotherapieService.createSeanceRadiotherapie(seanceRadiotherapie);
		
		return new ResponseEntity<Long>(seanceRadiotherapie.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/seanceRadiotherapie")	
	@PreAuthorize("hasRole('ROLE_UPDATE_SEANCERADIOTHERAPIE')")
	public ResponseEntity<SeanceRadiotherapieDto> updateSeanceRadiotherapie(@RequestBody SeanceRadiotherapieDto seanceRadiotherapie) throws Exception {

		if (seanceRadiotherapie.getId() == null)
			return new ResponseEntity<SeanceRadiotherapieDto>(HttpStatus.CONFLICT);

		seanceRadiotherapie = seanceRadiotherapieService.updateSeanceRadiotherapie(seanceRadiotherapie);

		return new ResponseEntity<SeanceRadiotherapieDto>(seanceRadiotherapie, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/seanceRadiotherapie/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_SEANCERADIOTHERAPIE')")
	public ResponseEntity<Void> deleteSeanceRadiotherapie(@RequestBody List<SeanceRadiotherapieDto> seanceRadiotherapieList) throws Exception {

		if (seanceRadiotherapieList == null || seanceRadiotherapieList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		seanceRadiotherapieService.deleteSeanceRadiotherapie(seanceRadiotherapieList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/seanceRadiotherapie/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<SeanceRadiotherapieDto>> getSeanceRadiotherapiesByCriteria(@RequestBody SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {

		List<SeanceRadiotherapieDto> list = seanceRadiotherapieService.findSeanceRadiotherapiesByCriteria(seanceRadiotherapieCriteria);

		if (StringUtil.isNoEmpty(seanceRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(seanceRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(seanceRadiotherapie -> new SeanceRadiotherapieDto().mappedCustomDto(seanceRadiotherapie, seanceRadiotherapieCriteria.getIncludes(), seanceRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<SeanceRadiotherapieDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<SeanceRadiotherapieDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/seanceRadiotherapie/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_SEANCERADIOTHERAPIE')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListSeanceRadiotherapies(@RequestBody SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {

		List<SeanceRadiotherapieDto> list = seanceRadiotherapieService.paginatedListSeanceRadiotherapies(seanceRadiotherapieCriteria,seanceRadiotherapieCriteria.getPage(),seanceRadiotherapieCriteria.getMaxResults(), seanceRadiotherapieCriteria.getSortOrder(), seanceRadiotherapieCriteria.getSortField());

		if (StringUtil.isNoEmpty(seanceRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(seanceRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(seanceRadiotherapie -> new SeanceRadiotherapieDto().mappedCustomDto(seanceRadiotherapie, seanceRadiotherapieCriteria.getIncludes(), seanceRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = seanceRadiotherapieService.getSeanceRadiotherapieDataSize(seanceRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/seanceRadiotherapie/exportSeanceRadiotherapies")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportSeanceRadiotherapies(@RequestBody SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {

		if (seanceRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		seanceRadiotherapieCriteria.setMaxResults(null);
		List<SeanceRadiotherapieDto> list = seanceRadiotherapieService.findSeanceRadiotherapiesByCriteria(seanceRadiotherapieCriteria);
		seanceRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(seanceRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/seanceRadiotherapie/getSeanceRadiotherapiesDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getSeanceRadiotherapieDataSize(@RequestBody SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {

		int count = seanceRadiotherapieService.getSeanceRadiotherapieDataSize(seanceRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

	@GetMapping("/api/seanceRadiotherapie/getPrescriptionRadiotherapieList")
	@PreAuthorize("hasAnyRole('ROLE_READ_SEANCERADIOTHERAPIE','ROLE_CREATE_SEANCERADIOTHERAPIE','ROLE_UPDATE_SEANCERADIOTHERAPIE')")	
	public @ResponseBody
	ResponseEntity<List<BusinessDto>> getPrescriptionRadiotherapieList() throws Exception {

//		PrescriptionRadiotherapieCriteria prescriptionRadiotherapieCriteria = new PrescriptionRadiotherapieCriteria();
//		prescriptionRadiotherapieCriteria.setOrderByAsc(new String[] { "observation" });
//
//		List<BusinessDto> list = CollectionUtils.emptyIfNull(prescriptionRadiotherapieService.findPrescriptionRadiotherapiesByCriteria(prescriptionRadiotherapieCriteria)).stream().map(prescriptionRadiotherapie -> new BusinessDto(prescriptionRadiotherapie.getId(), prescriptionRadiotherapie.getLabel())).collect(Collectors.toList());
//		if (list == null || list.isEmpty())
//			return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);
//
//		return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);
		return null;


	}


	@GetMapping("/api/seanceRadiotherapie/histSeanceRadiotherapie/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_SEANCERADIOTHERAPIE')")
	public ResponseEntity<AuditEntityDto> getHistSeanceRadiotherapieById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histSeanceRadiotherapie = seanceRadiotherapieService.getHistSeanceRadiotherapieById(id);

		return new ResponseEntity<AuditEntityDto>(histSeanceRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/seanceRadiotherapie/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_SEANCERADIOTHERAPIE')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistSeanceRadiotherapies(@RequestBody HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception {

		List<AuditEntityDto> list = seanceRadiotherapieService.paginatedListHistSeanceRadiotherapies(histSeanceRadiotherapieCriteria,histSeanceRadiotherapieCriteria.getPage(), histSeanceRadiotherapieCriteria.getMaxResults(), histSeanceRadiotherapieCriteria.getSortOrder(), histSeanceRadiotherapieCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = seanceRadiotherapieService.getHistSeanceRadiotherapieDataSize(histSeanceRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/seanceRadiotherapie/exportSeanceRadiotherapiesHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportSeanceRadiotherapiesHist(@RequestBody HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception {

		if (histSeanceRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histSeanceRadiotherapieCriteria.setMaxResults(null);
		List<AuditEntityDto> list = seanceRadiotherapieService.findSeanceRadiotherapiesHistByCriteria(histSeanceRadiotherapieCriteria);
		histSeanceRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(histSeanceRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/seanceRadiotherapie/getHistSeanceRadiotherapiesDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistSeanceRadiotherapieDataSize(@RequestBody HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception {

		int count = seanceRadiotherapieService.getHistSeanceRadiotherapieDataSize(histSeanceRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}