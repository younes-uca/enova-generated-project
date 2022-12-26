package ma.enova.rth.ws;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.controller.BaseController;
import ma.enova.rth.zynerator.dto.BusinessDto;
import ma.enova.rth.zynerator.util.PaginatedList;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ProfilCriteria;
import ma.enova.rth.dao.criteria.core.UtilisateurCriteria;
import ma.enova.rth.dao.criteria.history.HistUtilisateurCriteria;
import ma.enova.rth.dto.ProfilDto;
import ma.enova.rth.dto.UtilisateurDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IProfilService;
import ma.enova.rth.service.facade.IUtilisateurService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager controller : Utilisateur
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class UtilisateurController extends BaseController {


    /**
     * Services metiers.
     */
    @Autowired
    private IUtilisateurService utilisateurService;
    @Autowired
    private IProfilService profilService;

    @GetMapping("/api/utilisateur/{id}")
    @PreAuthorize("hasRole('ROLE_READ_UTILISATEUR')")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

        UtilisateurDto utilisateur = utilisateurService.getUtilisateurById(id);

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
            utilisateur = new UtilisateurDto().mappedCustomDto(utilisateur, includes, excludes);

        return new ResponseEntity<UtilisateurDto>(utilisateur, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur")
    @PreAuthorize("hasRole('ROLE_CREATE_UTILISATEUR')")
    public ResponseEntity<Long> addUtilisateur(@RequestBody UtilisateurDto utilisateur) throws Exception {

        utilisateur = utilisateurService.createUtilisateur(utilisateur);

        return new ResponseEntity<Long>(utilisateur.getId(), HttpStatus.CREATED);

    }

    @PutMapping("/api/utilisateur")
    @PreAuthorize("hasRole('ROLE_UPDATE_UTILISATEUR')")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(@RequestBody UtilisateurDto utilisateur) throws Exception {

        if (utilisateur.getId() == null)
            return new ResponseEntity<UtilisateurDto>(HttpStatus.CONFLICT);

        utilisateur = utilisateurService.updateUtilisateur(utilisateur);

        return new ResponseEntity<UtilisateurDto>(utilisateur, HttpStatus.OK);

    }

    @DeleteMapping("/api/utilisateur/delete")
    @PreAuthorize("hasRole('ROLE_DELETE_UTILISATEUR')")
    public ResponseEntity<Void> deleteUtilisateur(@RequestBody List<UtilisateurDto> utilisateurList) throws Exception {

        if (utilisateurList == null || utilisateurList.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        utilisateurService.deleteUtilisateur(utilisateurList);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @GetMapping("/api/utilisateur/resetPassword/{id}")
    @PreAuthorize("hasRole('ROLE_RESET_PASSWORD_UTILISATEUR')")
    public ResponseEntity<Void> resetUtilisateurPassword(@PathVariable("id") Long id) throws Exception {

        utilisateurService.resetUtilisateurPassword(id);

        return new ResponseEntity<Void>(HttpStatus.OK);

    }

    @GetMapping("/api/utilisateur/getUtilisateurHistList")

    public @ResponseBody
    ResponseEntity<List<UtilisateurDto>> getUtilisateurHistList() throws Exception {

        UtilisateurCriteria utilisateurCriteria = new UtilisateurCriteria();
        utilisateurCriteria.setOrderByAsc(new String[]{"username"});
        utilisateurCriteria.setEnabled("true");
        List<UtilisateurDto> list = utilisateurService.findUtilisateursByCriteria(utilisateurCriteria);

        if (list == null || list.isEmpty())
            return new ResponseEntity<List<UtilisateurDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<UtilisateurDto>>(list, HttpStatus.OK);

    }

    @PutMapping("/api/utilisateur/changePassword/{id}")
    @PreAuthorize("hasRole('ROLE_CHANGE_PASSWORD_UTILISATEUR')")
    public ResponseEntity<UtilisateurDto> updateUtilisateurPassword(@PathVariable("id") Long id, @RequestBody UtilisateurDto utilisateur) throws Exception {

        utilisateur.setId(id);
        utilisateur = utilisateurService.updateUtilisateurPassword(utilisateur);

        return new ResponseEntity<UtilisateurDto>(utilisateur, HttpStatus.OK);

    }

    @GetMapping("/api/utilisateur/getCurrentUserByDomain/{domaine}")

    public ResponseEntity<UtilisateurDetailsImpl> getCurrentUserByDomain(@PathVariable("domaine") Integer domain) throws Exception {
        UtilisateurDetailsImpl currentUser = null;
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        if (currentUser == null || currentUser.getUtilisateur() == null)
            return new ResponseEntity<UtilisateurDetailsImpl>(HttpStatus.NOT_FOUND);

        currentUser.getRolesByDomaine(domain);
        List<String> categorieRoles = utilisateurService.getCategorieRoleUtilisateur(currentUser.getId());
        currentUser.setCategorieRoles(categorieRoles);

        return new ResponseEntity<UtilisateurDetailsImpl>(currentUser, HttpStatus.OK);

    }

    @GetMapping("/api/utilisateur/getUtilisateurProfil")

    public ResponseEntity<ProfilDto> getUtilisateurProfil() throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (currentUser.getProfil() == null)
            return new ResponseEntity<ProfilDto>(HttpStatus.NOT_FOUND);

        ProfilDto profilDto = new ProfilDto();
        profilDto = profilDto.convertToDto(profilDto, currentUser.getProfil(), true, 0);

        return new ResponseEntity<ProfilDto>(profilDto, HttpStatus.OK);

    }

    @GetMapping("/api/utilisateur/getCurrentUtilisateur")

    public ResponseEntity<UtilisateurDto> getCurrentUtilisateur() throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (currentUser.getUtilisateur() == null)
            return new ResponseEntity<UtilisateurDto>(HttpStatus.NOT_FOUND);

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto = utilisateurDto.convertToDto(utilisateurDto, currentUser.getUtilisateur(), true, 0);

        return new ResponseEntity<UtilisateurDto>(utilisateurDto, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<UtilisateurDto>> getUtilisateursByCriteria(@RequestBody UtilisateurCriteria utilisateurCriteria) throws Exception {

        List<UtilisateurDto> list = utilisateurService.findUtilisateursByCriteria(utilisateurCriteria);

        if (StringUtil.isNoEmpty(utilisateurCriteria.getIncludes()) || StringUtil.isNoEmpty(utilisateurCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(utilisateur -> new UtilisateurDto().mappedCustomDto(utilisateur, utilisateurCriteria.getIncludes(), utilisateurCriteria.getExcludes())).collect(Collectors.toList());

        if (list == null || list.isEmpty())
            return new ResponseEntity<List<UtilisateurDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<UtilisateurDto>>(list, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur/paginatedListByCriteria")
    @PreAuthorize("hasRole('ROLE_READ_UTILISATEUR')")
    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListUtilisateurs(@RequestBody UtilisateurCriteria utilisateurCriteria) throws Exception {

        List<UtilisateurDto> list = utilisateurService.paginatedListUtilisateurs(utilisateurCriteria, utilisateurCriteria.getPage(), utilisateurCriteria.getMaxResults(), utilisateurCriteria.getSortOrder(), utilisateurCriteria.getSortField());

        if (StringUtil.isNoEmpty(utilisateurCriteria.getIncludes()) || StringUtil.isNoEmpty(utilisateurCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(utilisateur -> new UtilisateurDto().mappedCustomDto(utilisateur, utilisateurCriteria.getIncludes(), utilisateurCriteria.getExcludes())).collect(Collectors.toList());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = utilisateurService.getUtilisateurDataSize(utilisateurCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur/exportUtilisateurs")

    public @ResponseBody ResponseEntity<InputStreamResource> exportUtilisateurs(@RequestBody UtilisateurCriteria utilisateurCriteria) throws Exception {

        if (utilisateurCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        utilisateurCriteria.setMaxResults(null);
        List<UtilisateurDto> list = utilisateurService.findUtilisateursByCriteria(utilisateurCriteria);
        utilisateurCriteria.getExportModel().setList(list);
        return getExportedFileResource(utilisateurCriteria.getExportModel());

    }

    @PostMapping("/api/utilisateur/getUtilisateursDataSize")

    public @ResponseBody ResponseEntity<Integer> getUtilisateurDataSize(@RequestBody UtilisateurCriteria utilisateurCriteria) throws Exception {

        int count = utilisateurService.getUtilisateurDataSize(utilisateurCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


    @GetMapping("/api/utilisateur/getProfilList")
    @PreAuthorize("hasAnyRole('ROLE_READ_UTILISATEUR','ROLE_CREATE_UTILISATEUR','ROLE_UPDATE_UTILISATEUR')")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getProfilList() throws Exception {

        ProfilCriteria profilCriteria = new ProfilCriteria();
        profilCriteria.setOrderByAsc(new String[]{"libelle"});

        List<BusinessDto> list = CollectionUtils.emptyIfNull(profilService.findProfilsByCriteria(profilCriteria)).stream().map(profil -> new BusinessDto(profil.getId(), profil.getLabel())).collect(Collectors.toList());
        if (list == null || list.isEmpty())
            return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

    }


    @GetMapping("/api/utilisateur/histUtilisateur/{id}")
    @PreAuthorize("hasRole('ROLE_HIST_UTILISATEUR')")
    public ResponseEntity<AuditEntityDto> getHistUtilisateurById(@PathVariable("id") Long id) throws Exception {

        AuditEntityDto histUtilisateur = utilisateurService.getHistUtilisateurById(id);

        return new ResponseEntity<AuditEntityDto>(histUtilisateur, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur/paginatedListHistByCriteria")
    @PreAuthorize("hasRole('ROLE_HIST_UTILISATEUR')")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistUtilisateurs(@RequestBody HistUtilisateurCriteria histUtilisateurCriteria) throws Exception {

        List<AuditEntityDto> list = utilisateurService.paginatedListHistUtilisateurs(histUtilisateurCriteria, histUtilisateurCriteria.getPage(), histUtilisateurCriteria.getMaxResults(), histUtilisateurCriteria.getSortOrder(), histUtilisateurCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = utilisateurService.getHistUtilisateurDataSize(histUtilisateurCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/utilisateur/exportUtilisateursHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportUtilisateursHist(@RequestBody HistUtilisateurCriteria histUtilisateurCriteria) throws Exception {

        if (histUtilisateurCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histUtilisateurCriteria.setMaxResults(null);
        List<AuditEntityDto> list = utilisateurService.findUtilisateursHistByCriteria(histUtilisateurCriteria);
        histUtilisateurCriteria.getExportModel().setList(list);
        return getExportedFileResource(histUtilisateurCriteria.getExportModel());

    }

    @PostMapping("/api/utilisateur/getHistUtilisateursDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistUtilisateurDataSize(@RequestBody HistUtilisateurCriteria histUtilisateurCriteria) throws Exception {

        int count = utilisateurService.getHistUtilisateurDataSize(histUtilisateurCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}