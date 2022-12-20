package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.BusinessDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.converter.PrescriptionRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.*;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.service.facade.*;
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
 * Manager controller : PrescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class PrescriptionRadiotherapieController extends AbstractController<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, HistPrescriptionRadiotherapie, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria, IPrescriptionRadiotherapieService, PrescriptionRadiotherapieConverter> {

    /**
     * Services metiers.
     */
    @Autowired
    private IPrescriptionRadiotherapieService prescriptionRadiotherapieService;
    @Autowired
    private IViseeService viseeService;
    @Autowired
    private IOrganeService organeService;
    @Autowired
    private IPatientService patientService;
    @Autowired
    private IModaliteRadiotherapieService modaliteRadiotherapieService;
    @Autowired
    private IPersonnelService personnelService;
    @Autowired
    private IProtocoleInclusionService protocoleInclusionService;
    @Autowired
    private IFrequenceRadiotherapieService frequenceRadiotherapieService;

    @Autowired
    private PrescriptionRadiotherapieConverter prescriptionRadiotherapieConverter;

    public PrescriptionRadiotherapieController(IPrescriptionRadiotherapieService service, PrescriptionRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("/api/prescriptionRadiotherapie/{id}")
    public ResponseEntity<PrescriptionRadiotherapieDto> getPrescriptionRadiotherapieById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return findById(id, includes, excludes);

    }

    @PostMapping("/api/prescriptionRadiotherapie")
    public ResponseEntity<Long> addPrescriptionRadiotherapie(@RequestBody PrescriptionRadiotherapieDto prescriptionRadiotherapie) throws Exception {
        return save(prescriptionRadiotherapie);
    }


    @PutMapping("/api/prescriptionRadiotherapie")
    public ResponseEntity<PrescriptionRadiotherapieDto> updatePrescriptionRadiotherapie(@RequestBody PrescriptionRadiotherapieDto prescriptionRadiotherapie) throws Exception {
        return update(prescriptionRadiotherapie);
    }

    @DeleteMapping("/api/prescriptionRadiotherapie/delete")
    public ResponseEntity<Void> deletePrescriptionRadiotherapie(@RequestBody List<PrescriptionRadiotherapieDto> prescriptionRadiotherapieList) throws Exception {
        return delete(prescriptionRadiotherapieList);
    }


    @PostMapping("/api/prescriptionRadiotherapie/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<PrescriptionRadiotherapieDto>> getPrescriptionRadiotherapiesByCriteria(@RequestBody PrescriptionRadiotherapieCriteria prescriptionRadiotherapieCriteria) throws Exception {
        return findMultipleByCriteria(prescriptionRadiotherapieCriteria);
    }

    @PostMapping("/api/prescriptionRadiotherapie/paginatedListByCriteria")
    public ResponseEntity<PaginatedList> paginatedListPrescriptionRadiotherapies(@RequestBody PrescriptionRadiotherapieCriteria prescriptionRadiotherapieCriteria) throws Exception {
        return findPaginatedByCriteria(prescriptionRadiotherapieCriteria);
    }

    @PostMapping("/api/prescriptionRadiotherapie/exportPrescriptionRadiotherapies")
    public ResponseEntity<InputStreamResource> exportPrescriptionRadiotherapies(@RequestBody PrescriptionRadiotherapieCriteria prescriptionRadiotherapieCriteria) throws Exception {
        return export(prescriptionRadiotherapieCriteria);

    }

    @PostMapping("/api/prescriptionRadiotherapie/getPrescriptionRadiotherapiesDataSize")

    public @ResponseBody ResponseEntity<Integer> getPrescriptionRadiotherapieDataSize(@RequestBody PrescriptionRadiotherapieCriteria prescriptionRadiotherapieCriteria) throws Exception {

        int count = prescriptionRadiotherapieService.getDataSize(prescriptionRadiotherapieCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


    @GetMapping("/api/prescriptionRadiotherapie/getProtocoleInclusionList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getProtocoleInclusionList() throws Exception {

        ProtocoleInclusionCriteria protocoleInclusionCriteria = new ProtocoleInclusionCriteria();
        protocoleInclusionCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(protocoleInclusionService.findProtocoleInclusionsByCriteria(protocoleInclusionCriteria)).stream().map(protocoleInclusion -> new BusinessDto(protocoleInclusion.getId(), protocoleInclusion.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getViseeList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getViseeList() throws Exception {

        ViseeCriteria viseeCriteria = new ViseeCriteria();
        viseeCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(viseeService.findViseesByCriteria(viseeCriteria)).stream().map(visee -> new BusinessDto(visee.getId(), visee.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getMedecinPrescripteurList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getMedecinPrescripteurList() throws Exception {

        PersonnelCriteria medecinPrescripteurCriteria = new PersonnelCriteria();
        medecinPrescripteurCriteria.setOrderByAsc(new String[]{"nom"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(personnelService.findPersonnelsByCriteria(medecinPrescripteurCriteria)).stream().map(medecinPrescripteur -> new BusinessDto(medecinPrescripteur.getId(), medecinPrescripteur.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getPatientList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getPatientList() throws Exception {

        PatientCriteria patientCriteria = new PatientCriteria();
        patientCriteria.setOrderByAsc(new String[]{"nom"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(patientService.findPatientsByCriteria(patientCriteria)).stream().map(patient -> new BusinessDto(patient.getId(), patient.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getOrganeList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getOrganeList() throws Exception {

        OrganeCriteria organeCriteria = new OrganeCriteria();
        organeCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(organeService.findOrganesByCriteria(organeCriteria)).stream().map(organe -> new BusinessDto(organe.getId(), organe.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getModaliteRadiotherapieList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getModaliteRadiotherapieList() throws Exception {

        ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria = new ModaliteRadiotherapieCriteria();
        modaliteRadiotherapieCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(modaliteRadiotherapieService.findModaliteRadiotherapiesByCriteria(modaliteRadiotherapieCriteria)).stream().map(modaliteRadiotherapie -> new BusinessDto(modaliteRadiotherapie.getId(), modaliteRadiotherapie.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }

    @GetMapping("/api/prescriptionRadiotherapie/getFrequenceRadiotherapieList")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getFrequenceRadiotherapieList() throws Exception {

        FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria = new FrequenceRadiotherapieCriteria();
        frequenceRadiotherapieCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(frequenceRadiotherapieService.findFrequenceRadiotherapiesByCriteria(frequenceRadiotherapieCriteria)).stream().map(frequenceRadiotherapie -> new BusinessDto(frequenceRadiotherapie.getId(), frequenceRadiotherapie.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }


    @GetMapping("/api/prescriptionRadiotherapie/histPrescriptionRadiotherapie/{id}")
    public ResponseEntity<AuditEntityDto> getHistPrescriptionRadiotherapieById(@PathVariable("id") Long id) throws Exception {

        AuditEntityDto histPrescriptionRadiotherapie = prescriptionRadiotherapieService.findHistoryById(id);

        return new ResponseEntity<AuditEntityDto>(histPrescriptionRadiotherapie, HttpStatus.OK);

    }

    @PostMapping("/api/prescriptionRadiotherapie/paginatedListHistByCriteria")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistPrescriptionRadiotherapies(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        List<AuditEntityDto> list = prescriptionRadiotherapieService.findHistoryPaginatedByCriteria(histPrescriptionRadiotherapieCriteria, histPrescriptionRadiotherapieCriteria.getPage(), histPrescriptionRadiotherapieCriteria.getMaxResults(), histPrescriptionRadiotherapieCriteria.getSortOrder(), histPrescriptionRadiotherapieCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = prescriptionRadiotherapieService.getHistoryDataSize(histPrescriptionRadiotherapieCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/prescriptionRadiotherapie/exportPrescriptionRadiotherapiesHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportPrescriptionRadiotherapiesHist(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        if (histPrescriptionRadiotherapieCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histPrescriptionRadiotherapieCriteria.setMaxResults(null);
//		List<AuditEntityDto> list = prescriptionRadiotherapieService.findHistoryPaginatedByCriteria(histPrescriptionRadiotherapieCriteria);
//		histPrescriptionRadiotherapieCriteria.getExportModel().setList(list);
//		return getExportedFileResource(histPrescriptionRadiotherapieCriteria.getExportModel());
        return null;//TODO correct this bug

    }

    @PostMapping("/api/prescriptionRadiotherapie/getHistPrescriptionRadiotherapiesDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistPrescriptionRadiotherapieDataSize(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        int count = prescriptionRadiotherapieService.getHistoryDataSize(histPrescriptionRadiotherapieCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}