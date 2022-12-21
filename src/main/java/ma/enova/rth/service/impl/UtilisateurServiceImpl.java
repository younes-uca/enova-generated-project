package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.BusinessRuleException;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.UtilisateurCriteria;
import ma.enova.rth.dao.criteria.history.HistUtilisateurCriteria;
import ma.enova.rth.dao.facade.core.IUtilisateurRepository;
import ma.enova.rth.dao.facade.history.IHistUtilisateurRepository;
import ma.enova.rth.dao.specfication.core.UtilisateurSpecification;
import ma.enova.rth.dao.specfication.history.HistUtilisateurSpecification;
import ma.enova.rth.domain.core.Utilisateur;
import ma.enova.rth.domain.historique.HistUtilisateur;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.UtilisateurDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IUtilisateurService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation du service Iutilisateur
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "utilisateurService")
public class UtilisateurServiceImpl implements IUtilisateurService {

    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    @Autowired
    private IHistUtilisateurRepository histUtilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Value("${default.password}")
    private String defaultPassword;


    /**
     * createUtilisateur.
     *
     * @return Utilisateur
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateurDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long etablissementId = currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null;
        if (currentUser.getEtablissement() != null)
            utilisateurDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));


        Utilisateur utilisateur = new Utilisateur();
        utilisateurDto.setEnabled(true);
        String encodePassword = passwordEncoder.encode(defaultPassword);
        utilisateur.setPassword(encodePassword);
        utilisateur = utilisateurDto.convertToEntity(utilisateur, utilisateurDto);
        Utilisateur newUtilisateur = utilisateurRepository.save(utilisateur);
        utilisateurDto.setId(newUtilisateur.getId());


        return utilisateurDto;
    }

    /**
     * updateUtilisateur.
     *
     * @return Utilisateur
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public UtilisateurDto updateUtilisateur(UtilisateurDto utilisateurDto) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long etablissementId = currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null;

        saveUtilisateurAuditData(utilisateurDto, ACTION_TYPE.UPDATE);

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), utilisateurDto.getId().toString()}));
        utilisateur = utilisateurDto.convertToEntity(utilisateur, utilisateurDto);
        utilisateurRepository.saveAndFlush(utilisateur);

        return utilisateurDto;
    }

    /**
     * getUtilisateurById.
     *
     * @param utilisateurId
     * @return Utilisateur
     * @throws Exception
     */
    public UtilisateurDto getUtilisateurById(Long utilisateurId) throws Exception {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), utilisateurId.toString()}));

        return new UtilisateurDto(utilisateur, true, 0);

    }

    /**
     * resetUtilisateurPassword.
     *
     * @param utilisateurId
     * @return Utilisateur
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public UtilisateurDto resetUtilisateurPassword(Long utilisateurId) throws Exception {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), utilisateurId.toString()}));

        String encodePassword = passwordEncoder.encode(defaultPassword);
        utilisateur.setPassword(encodePassword);
        utilisateur = utilisateurRepository.saveAndFlush(utilisateur);

        return new UtilisateurDto(utilisateur);
    }

    /**
     * updateUtilisateurPassword.
     *
     * @return Utilisateur
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public UtilisateurDto updateUtilisateurPassword(UtilisateurDto utilisateurBean) throws Exception {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurBean.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), utilisateurBean.getId().toString()}));

        if (!passwordEncoder.matches(utilisateurBean.getOldPassword(), utilisateur.getPassword()))
            throw new BusinessRuleException("errors.incorrect.oldPassword");

        String encodePassword = passwordEncoder.encode(utilisateurBean.getNewPassword());
        utilisateur.setPassword(encodePassword);
        utilisateur = utilisateurRepository.saveAndFlush(utilisateur);

        UtilisateurDto utilisateurDto = new UtilisateurDto();
        utilisateurDto = utilisateurDto.convertToDto(utilisateurDto, utilisateur, true, 0);

        return utilisateurDto;
    }

    /**
     * loadUserByUsername.
     *
     * @param username
     * @return Utilisateur
     * @throws Exception
     */
    public Utilisateur loadUserByUsername(String username) throws Exception {

        Utilisateur utilisateur = null;//utilisateurRepository.loadUserByUsername(username);

        if (utilisateur == null)
            throw new EntityNotFoundException("errors.username.notFound", new String[]{username});

        return utilisateur;

    }

    @Override
    public List<String> getCategorieRoleUtilisateur(Long id) throws Exception {
        return null;
    }

    public List<String> getUtilisateur(Long utilisateurId) throws Exception {

        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId).get();
        List<String> result = new ArrayList<>();

        List<Long> ids = null;//utilisateurRepository.getCategoriesRoleByProfil(utilisateur.getProfil().getId());

        if (ids != null && !ids.isEmpty()) {

            List<String> s = new ArrayList<>();

            if (s != null && !s.isEmpty())
                result.addAll(s);
        }

        return result;

    }


    /**
     * findUtilisateursByCriteria.
     *
     * @param utilisateurCriteria
     * @return List<Utilisateur>
     * @throws Exception
     */
    public List<UtilisateurDto> findUtilisateursByCriteria(UtilisateurCriteria utilisateurCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Utilisateur> specification = new UtilisateurSpecification(utilisateurCriteria);

        if (utilisateurCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, utilisateurCriteria.getMaxResults());
            return utilisateurRepository.findAll(specification, pageable).getContent().stream().map(utilisateur -> new UtilisateurDto(utilisateur)).collect(Collectors.toList());

        } else {
            return utilisateurRepository.findAll(specification).stream().map(utilisateur -> new UtilisateurDto(utilisateur)).collect(Collectors.toList());
        }

    }

    /**
     * findUtilisateurByCriteria.
     *
     * @param utilisateurCriteria
     * @return Utilisateur
     * @throws Exception
     */

    public UtilisateurDto findUtilisateurByCriteria(UtilisateurCriteria utilisateurCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Utilisateur> specification = new UtilisateurSpecification(utilisateurCriteria);

        Utilisateur utilisateur = utilisateurRepository.findOne(specification).orElse(null);

        UtilisateurDto utilisateurDto = null;
        if (utilisateur != null) {
            utilisateurDto = new UtilisateurDto();
            return new UtilisateurDto(utilisateur, true);
        }

        return utilisateurDto;
    }

    /**
     * paginatedListUtilisateurs.
     *
     * @param utilisateurCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Utilisateur>
     * @throws Exception
     */

    public List<UtilisateurDto> paginatedListUtilisateurs(UtilisateurCriteria utilisateurCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Utilisateur> specification = new UtilisateurSpecification(utilisateurCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return utilisateurRepository.findAll(specification, pageable).getContent().stream().map(utilisateur -> new UtilisateurDto(utilisateur)).collect(Collectors.toList());
    }

    /**
     * getUtilisateurDataSize.
     *
     * @param utilisateurCriteria
     * @return int
     * @throws Exception
     */
    public int getUtilisateurDataSize(UtilisateurCriteria utilisateurCriteria) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        utilisateurCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);

        Specification<Utilisateur> specification = new UtilisateurSpecification(utilisateurCriteria, true);

        return ((Long) utilisateurRepository.count(specification)).intValue();

    }

    /**
     * deleteUtilisateur.
     *
     * @param utilisateurList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteUtilisateur(List<UtilisateurDto> utilisateurList) throws Exception {

        UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (utilisateurList != null)
            for (UtilisateurDto utilisateurDto : utilisateurList) {
                Utilisateur toBeDeleted = utilisateurRepository.findById(utilisateurDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Utilisateur.class.getSimpleName(), utilisateurDto.getId().toString()}));
                toBeDeleted.setEnabled(false);

                utilisateurRepository.saveAndFlush(toBeDeleted);

                HistUtilisateur histUtilisateur = new HistUtilisateur();
                histUtilisateur.setActionType(ACTION_TYPE.DELETE.name());
                histUtilisateur.setObjectName("utilisateur");
                histUtilisateur.setObjectId(utilisateurDto.getId());
                String utilisateurValue = new ObjectMapper().writeValueAsString(utilisateurDto);
                histUtilisateur.setData(utilisateurValue);
                histUtilisateur.setUserId(currentUser.getId());
                histUtilisateur.setUsername(currentUser.getUsername());
                histUtilisateur.setDate(LocalDateTime.now());
                histUtilisateurRepository.save(histUtilisateur);
            }
    }


    private void saveUtilisateurAuditData(UtilisateurDto utilisateur, ACTION_TYPE action) throws Exception {

        UtilisateurDto oldUtilisateur = getUtilisateurById(utilisateur.getId());
        if (Utils.compareObjectsDiff(utilisateur, oldUtilisateur)) {

            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            HistUtilisateur histUtilisateur = new HistUtilisateur();
            histUtilisateur.setActionType(action.name());
            histUtilisateur.setObjectName("utilisateur");
            histUtilisateur.setObjectId(utilisateur.getId());
            histUtilisateur.setUserId(currentUser.getId());
            histUtilisateur.setUsername(currentUser.getUsername());
            String utilisateurValue = new ObjectMapper().writeValueAsString(utilisateur);
            histUtilisateur.setData(utilisateurValue);
            histUtilisateur.setDate(LocalDateTime.now());
            histUtilisateurRepository.save(histUtilisateur);
        }
    }

    /**
     * getHistUtilisateurById.
     *
     * @param histUtilisateurId
     * @return AuditEntityDto
     * @throws Exception
     */
    public AuditEntityDto getHistUtilisateurById(Long histUtilisateurId) throws Exception {

        HistUtilisateur histUtilisateur = histUtilisateurRepository.findById(histUtilisateurId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{HistUtilisateur.class.getSimpleName(), histUtilisateurId.toString()}));

        return new AuditEntityDto(histUtilisateur);

    }


    /**
     * paginatedListHistUtilisateurs.
     *
     * @param histUtilisateurCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<HistUtilisateur>
     * @throws Exception
     */

    public List<AuditEntityDto> paginatedListHistUtilisateurs(HistUtilisateurCriteria histUtilisateurCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<HistUtilisateur> specification = new HistUtilisateurSpecification(histUtilisateurCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return histUtilisateurRepository.findAll(specification, pageable).getContent().stream().map(utilisateur -> new AuditEntityDto(utilisateur)).collect(Collectors.toList());
    }

    /**
     * findUtilisateursHistByCriteria.
     *
     * @return List<HistUtilisateur>
     * @throws Exception
     */
    public List<AuditEntityDto> findUtilisateursHistByCriteria(HistUtilisateurCriteria histUtilisateurCriteria) throws Exception {

        Specification<HistUtilisateur> specification = new HistUtilisateurSpecification(histUtilisateurCriteria);

        if (histUtilisateurCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, histUtilisateurCriteria.getMaxResults());
            return histUtilisateurRepository.findAll(specification, pageable).getContent().stream().map(utilisateur -> new AuditEntityDto(utilisateur)).collect(Collectors.toList());

        } else {
            return histUtilisateurRepository.findAll(specification).stream().map(utilisateur -> new AuditEntityDto(utilisateur)).collect(Collectors.toList());
        }

    }

    /**
     * getHistUtilisateurDataSize.
     *
     * @param histUtilisateurCriteria
     * @return int
     * @throws Exception
     */
    public int getHistUtilisateurDataSize(HistUtilisateurCriteria histUtilisateurCriteria) throws Exception {

        Specification<HistUtilisateur> specification = new HistUtilisateurSpecification(histUtilisateurCriteria, true);

        return ((Long) histUtilisateurRepository.count(specification)).intValue();

    }

}