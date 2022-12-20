package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
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
 * Manager controller : FrequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class FrequenceRadiotherapieController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IFrequenceRadiotherapieService frequenceRadiotherapieService;

	@GetMapping("/api/frequenceRadiotherapie/{id}")
	@PreAuthorize("hasRole('ROLE_READ_FREQUENCERADIOTHERAPIE')")
	public ResponseEntity<FrequenceRadiotherapieDto> getFrequenceRadiotherapieById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		FrequenceRadiotherapieDto frequenceRadiotherapie = frequenceRadiotherapieService.getFrequenceRadiotherapieById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			frequenceRadiotherapie = new FrequenceRadiotherapieDto().mappedCustomDto(frequenceRadiotherapie, includes, excludes);

		return new ResponseEntity<FrequenceRadiotherapieDto>(frequenceRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/frequenceRadiotherapie")
	@PreAuthorize("hasRole('ROLE_CREATE_FREQUENCERADIOTHERAPIE')")
	public ResponseEntity<Long> addFrequenceRadiotherapie(@RequestBody FrequenceRadiotherapieDto frequenceRadiotherapie) throws Exception {

		frequenceRadiotherapie = frequenceRadiotherapieService.createFrequenceRadiotherapie(frequenceRadiotherapie);
		
		return new ResponseEntity<Long>(frequenceRadiotherapie.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/frequenceRadiotherapie")	
	@PreAuthorize("hasRole('ROLE_UPDATE_FREQUENCERADIOTHERAPIE')")
	public ResponseEntity<FrequenceRadiotherapieDto> updateFrequenceRadiotherapie(@RequestBody FrequenceRadiotherapieDto frequenceRadiotherapie) throws Exception {

		if (frequenceRadiotherapie.getId() == null)
			return new ResponseEntity<FrequenceRadiotherapieDto>(HttpStatus.CONFLICT);

		frequenceRadiotherapie = frequenceRadiotherapieService.updateFrequenceRadiotherapie(frequenceRadiotherapie);

		return new ResponseEntity<FrequenceRadiotherapieDto>(frequenceRadiotherapie, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/frequenceRadiotherapie/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_FREQUENCERADIOTHERAPIE')")
	public ResponseEntity<Void> deleteFrequenceRadiotherapie(@RequestBody List<FrequenceRadiotherapieDto> frequenceRadiotherapieList) throws Exception {

		if (frequenceRadiotherapieList == null || frequenceRadiotherapieList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		frequenceRadiotherapieService.deleteFrequenceRadiotherapie(frequenceRadiotherapieList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/frequenceRadiotherapie/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<FrequenceRadiotherapieDto>> getFrequenceRadiotherapiesByCriteria(@RequestBody FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {

		List<FrequenceRadiotherapieDto> list = frequenceRadiotherapieService.findFrequenceRadiotherapiesByCriteria(frequenceRadiotherapieCriteria);

		if (StringUtil.isNoEmpty(frequenceRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(frequenceRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(frequenceRadiotherapie -> new FrequenceRadiotherapieDto().mappedCustomDto(frequenceRadiotherapie, frequenceRadiotherapieCriteria.getIncludes(), frequenceRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<FrequenceRadiotherapieDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<FrequenceRadiotherapieDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/frequenceRadiotherapie/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_FREQUENCERADIOTHERAPIE')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListFrequenceRadiotherapies(@RequestBody FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {

		List<FrequenceRadiotherapieDto> list = frequenceRadiotherapieService.paginatedListFrequenceRadiotherapies(frequenceRadiotherapieCriteria,frequenceRadiotherapieCriteria.getPage(),frequenceRadiotherapieCriteria.getMaxResults(), frequenceRadiotherapieCriteria.getSortOrder(), frequenceRadiotherapieCriteria.getSortField());

		if (StringUtil.isNoEmpty(frequenceRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(frequenceRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(frequenceRadiotherapie -> new FrequenceRadiotherapieDto().mappedCustomDto(frequenceRadiotherapie, frequenceRadiotherapieCriteria.getIncludes(), frequenceRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = frequenceRadiotherapieService.getFrequenceRadiotherapieDataSize(frequenceRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/frequenceRadiotherapie/exportFrequenceRadiotherapies")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportFrequenceRadiotherapies(@RequestBody FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {

		if (frequenceRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		frequenceRadiotherapieCriteria.setMaxResults(null);
		List<FrequenceRadiotherapieDto> list = frequenceRadiotherapieService.findFrequenceRadiotherapiesByCriteria(frequenceRadiotherapieCriteria);
		frequenceRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(frequenceRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/frequenceRadiotherapie/getFrequenceRadiotherapiesDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getFrequenceRadiotherapieDataSize(@RequestBody FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {

		int count = frequenceRadiotherapieService.getFrequenceRadiotherapieDataSize(frequenceRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/frequenceRadiotherapie/histFrequenceRadiotherapie/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_FREQUENCERADIOTHERAPIE')")
	public ResponseEntity<AuditEntityDto> getHistFrequenceRadiotherapieById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histFrequenceRadiotherapie = frequenceRadiotherapieService.getHistFrequenceRadiotherapieById(id);

		return new ResponseEntity<AuditEntityDto>(histFrequenceRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/frequenceRadiotherapie/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_FREQUENCERADIOTHERAPIE')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistFrequenceRadiotherapies(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

		List<AuditEntityDto> list = frequenceRadiotherapieService.paginatedListHistFrequenceRadiotherapies(histFrequenceRadiotherapieCriteria,histFrequenceRadiotherapieCriteria.getPage(), histFrequenceRadiotherapieCriteria.getMaxResults(), histFrequenceRadiotherapieCriteria.getSortOrder(), histFrequenceRadiotherapieCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = frequenceRadiotherapieService.getHistFrequenceRadiotherapieDataSize(histFrequenceRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/frequenceRadiotherapie/exportFrequenceRadiotherapiesHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportFrequenceRadiotherapiesHist(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

		if (histFrequenceRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histFrequenceRadiotherapieCriteria.setMaxResults(null);
		List<AuditEntityDto> list = frequenceRadiotherapieService.findFrequenceRadiotherapiesHistByCriteria(histFrequenceRadiotherapieCriteria);
		histFrequenceRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(histFrequenceRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/frequenceRadiotherapie/getHistFrequenceRadiotherapiesDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistFrequenceRadiotherapieDataSize(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

		int count = frequenceRadiotherapieService.getHistFrequenceRadiotherapieDataSize(histFrequenceRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}