package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
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
 * Manager controller : ModaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class ModaliteRadiotherapieController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IModaliteRadiotherapieService modaliteRadiotherapieService;

	@GetMapping("/api/modaliteRadiotherapie/{id}")
	@PreAuthorize("hasRole('ROLE_READ_MODALITERADIOTHERAPIE')")
	public ResponseEntity<ModaliteRadiotherapieDto> getModaliteRadiotherapieById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		ModaliteRadiotherapieDto modaliteRadiotherapie = modaliteRadiotherapieService.getModaliteRadiotherapieById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			modaliteRadiotherapie = new ModaliteRadiotherapieDto().mappedCustomDto(modaliteRadiotherapie, includes, excludes);

		return new ResponseEntity<ModaliteRadiotherapieDto>(modaliteRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/modaliteRadiotherapie")
	@PreAuthorize("hasRole('ROLE_CREATE_MODALITERADIOTHERAPIE')")
	public ResponseEntity<Long> addModaliteRadiotherapie(@RequestBody ModaliteRadiotherapieDto modaliteRadiotherapie) throws Exception {

		modaliteRadiotherapie = modaliteRadiotherapieService.createModaliteRadiotherapie(modaliteRadiotherapie);
		
		return new ResponseEntity<Long>(modaliteRadiotherapie.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/modaliteRadiotherapie")	
	@PreAuthorize("hasRole('ROLE_UPDATE_MODALITERADIOTHERAPIE')")
	public ResponseEntity<ModaliteRadiotherapieDto> updateModaliteRadiotherapie(@RequestBody ModaliteRadiotherapieDto modaliteRadiotherapie) throws Exception {

		if (modaliteRadiotherapie.getId() == null)
			return new ResponseEntity<ModaliteRadiotherapieDto>(HttpStatus.CONFLICT);

		modaliteRadiotherapie = modaliteRadiotherapieService.updateModaliteRadiotherapie(modaliteRadiotherapie);

		return new ResponseEntity<ModaliteRadiotherapieDto>(modaliteRadiotherapie, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/modaliteRadiotherapie/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_MODALITERADIOTHERAPIE')")
	public ResponseEntity<Void> deleteModaliteRadiotherapie(@RequestBody List<ModaliteRadiotherapieDto> modaliteRadiotherapieList) throws Exception {

		if (modaliteRadiotherapieList == null || modaliteRadiotherapieList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		modaliteRadiotherapieService.deleteModaliteRadiotherapie(modaliteRadiotherapieList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/modaliteRadiotherapie/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<ModaliteRadiotherapieDto>> getModaliteRadiotherapiesByCriteria(@RequestBody ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {

		List<ModaliteRadiotherapieDto> list = modaliteRadiotherapieService.findModaliteRadiotherapiesByCriteria(modaliteRadiotherapieCriteria);

		if (StringUtil.isNoEmpty(modaliteRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(modaliteRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(modaliteRadiotherapie -> new ModaliteRadiotherapieDto().mappedCustomDto(modaliteRadiotherapie, modaliteRadiotherapieCriteria.getIncludes(), modaliteRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<ModaliteRadiotherapieDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ModaliteRadiotherapieDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/modaliteRadiotherapie/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_MODALITERADIOTHERAPIE')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListModaliteRadiotherapies(@RequestBody ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {

		List<ModaliteRadiotherapieDto> list = modaliteRadiotherapieService.paginatedListModaliteRadiotherapies(modaliteRadiotherapieCriteria,modaliteRadiotherapieCriteria.getPage(),modaliteRadiotherapieCriteria.getMaxResults(), modaliteRadiotherapieCriteria.getSortOrder(), modaliteRadiotherapieCriteria.getSortField());

		if (StringUtil.isNoEmpty(modaliteRadiotherapieCriteria.getIncludes()) || StringUtil.isNoEmpty(modaliteRadiotherapieCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(modaliteRadiotherapie -> new ModaliteRadiotherapieDto().mappedCustomDto(modaliteRadiotherapie, modaliteRadiotherapieCriteria.getIncludes(), modaliteRadiotherapieCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = modaliteRadiotherapieService.getModaliteRadiotherapieDataSize(modaliteRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/modaliteRadiotherapie/exportModaliteRadiotherapies")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportModaliteRadiotherapies(@RequestBody ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {

		if (modaliteRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		modaliteRadiotherapieCriteria.setMaxResults(null);
		List<ModaliteRadiotherapieDto> list = modaliteRadiotherapieService.findModaliteRadiotherapiesByCriteria(modaliteRadiotherapieCriteria);
		modaliteRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(modaliteRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/modaliteRadiotherapie/getModaliteRadiotherapiesDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getModaliteRadiotherapieDataSize(@RequestBody ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {

		int count = modaliteRadiotherapieService.getModaliteRadiotherapieDataSize(modaliteRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/modaliteRadiotherapie/histModaliteRadiotherapie/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_MODALITERADIOTHERAPIE')")
	public ResponseEntity<AuditEntityDto> getHistModaliteRadiotherapieById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histModaliteRadiotherapie = modaliteRadiotherapieService.getHistModaliteRadiotherapieById(id);

		return new ResponseEntity<AuditEntityDto>(histModaliteRadiotherapie, HttpStatus.OK);

	}
	
	@PostMapping("/api/modaliteRadiotherapie/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_MODALITERADIOTHERAPIE')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistModaliteRadiotherapies(@RequestBody HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception {

		List<AuditEntityDto> list = modaliteRadiotherapieService.paginatedListHistModaliteRadiotherapies(histModaliteRadiotherapieCriteria,histModaliteRadiotherapieCriteria.getPage(), histModaliteRadiotherapieCriteria.getMaxResults(), histModaliteRadiotherapieCriteria.getSortOrder(), histModaliteRadiotherapieCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = modaliteRadiotherapieService.getHistModaliteRadiotherapieDataSize(histModaliteRadiotherapieCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/modaliteRadiotherapie/exportModaliteRadiotherapiesHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportModaliteRadiotherapiesHist(@RequestBody HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception {

		if (histModaliteRadiotherapieCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histModaliteRadiotherapieCriteria.setMaxResults(null);
		List<AuditEntityDto> list = modaliteRadiotherapieService.findModaliteRadiotherapiesHistByCriteria(histModaliteRadiotherapieCriteria);
		histModaliteRadiotherapieCriteria.getExportModel().setList(list);
		return getExportedFileResource(histModaliteRadiotherapieCriteria.getExportModel());
	
	}

	@PostMapping("/api/modaliteRadiotherapie/getHistModaliteRadiotherapiesDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistModaliteRadiotherapieDataSize(@RequestBody HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception {

		int count = modaliteRadiotherapieService.getHistModaliteRadiotherapieDataSize(histModaliteRadiotherapieCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}