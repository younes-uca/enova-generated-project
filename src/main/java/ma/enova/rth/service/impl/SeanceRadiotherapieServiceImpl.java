package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.ISeanceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistSeanceRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.SeanceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistSeanceRadiotherapieSpecification;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
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
 * Implementation du service IseanceRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "seanceRadiotherapieService")
public class SeanceRadiotherapieServiceImpl implements ISeanceRadiotherapieService {

	@Autowired
	private ISeanceRadiotherapieRepository seanceRadiotherapieRepository;
	
	@Autowired
	private IHistSeanceRadiotherapieRepository histSeanceRadiotherapieRepository;



	/**
	 * createSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapie
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public SeanceRadiotherapieDto createSeanceRadiotherapie(SeanceRadiotherapieDto seanceRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			seanceRadiotherapieDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		SeanceRadiotherapie seanceRadiotherapie = new SeanceRadiotherapie();
		seanceRadiotherapie = seanceRadiotherapieDto.convertToEntity(seanceRadiotherapie, seanceRadiotherapieDto);
		SeanceRadiotherapie newSeanceRadiotherapie = seanceRadiotherapieRepository.save(seanceRadiotherapie);
		seanceRadiotherapieDto.setId(newSeanceRadiotherapie.getId());		



			
		return seanceRadiotherapieDto;
	}
	
	/**
	 * updateSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapie
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public SeanceRadiotherapieDto updateSeanceRadiotherapie(SeanceRadiotherapieDto seanceRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		saveSeanceRadiotherapieAuditData(seanceRadiotherapieDto, ACTION_TYPE.UPDATE);

		SeanceRadiotherapie seanceRadiotherapie = seanceRadiotherapieRepository.findById(seanceRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { SeanceRadiotherapie.class.getSimpleName(), seanceRadiotherapieDto.getId().toString() }));
		seanceRadiotherapie = seanceRadiotherapieDto.convertToEntity(seanceRadiotherapie, seanceRadiotherapieDto);
		seanceRadiotherapieRepository.saveAndFlush(seanceRadiotherapie);
		
		return seanceRadiotherapieDto;
	}
	
	/**
	 * getSeanceRadiotherapieById.
	 * 
	 * @param seanceRadiotherapieId
	 * @return SeanceRadiotherapie
	 * @throws Exception
	 */
	public SeanceRadiotherapieDto getSeanceRadiotherapieById(Long seanceRadiotherapieId) throws Exception {

		SeanceRadiotherapie seanceRadiotherapie = seanceRadiotherapieRepository.findById(seanceRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { SeanceRadiotherapie.class.getSimpleName(), seanceRadiotherapieId.toString() }));

		return  new SeanceRadiotherapieDto(seanceRadiotherapie, true, 0);

	}
	

	/**
	 * findSeanceRadiotherapiesByCriteria.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return List<SeanceRadiotherapie>
	 * @throws Exception
	 */	
	public List<SeanceRadiotherapieDto> findSeanceRadiotherapiesByCriteria(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		seanceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<SeanceRadiotherapie> specification = new SeanceRadiotherapieSpecification(seanceRadiotherapieCriteria);
		
		if (seanceRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, seanceRadiotherapieCriteria.getMaxResults());
			return seanceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(seanceRadiotherapie -> new SeanceRadiotherapieDto(seanceRadiotherapie)).collect(Collectors.toList());

		} else {
			return seanceRadiotherapieRepository.findAll(specification).stream().map(seanceRadiotherapie -> new SeanceRadiotherapieDto(seanceRadiotherapie)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findSeanceRadiotherapieByCriteria.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return SeanceRadiotherapie 
	 * @throws Exception
	 */
	
	public SeanceRadiotherapieDto findSeanceRadiotherapieByCriteria(SeanceRadiotherapieCriteria  seanceRadiotherapieCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		seanceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<SeanceRadiotherapie> specification = new SeanceRadiotherapieSpecification(seanceRadiotherapieCriteria);
		
		SeanceRadiotherapie seanceRadiotherapie = seanceRadiotherapieRepository.findOne(specification).orElse(null);
		
		SeanceRadiotherapieDto seanceRadiotherapieDto = null;
		if (seanceRadiotherapie != null) {
			seanceRadiotherapieDto = new SeanceRadiotherapieDto();
			return new SeanceRadiotherapieDto(seanceRadiotherapie, true);
		}

		return seanceRadiotherapieDto;
	}

	/**
	 * paginatedListSeanceRadiotherapies.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<SeanceRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<SeanceRadiotherapieDto> paginatedListSeanceRadiotherapies(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		seanceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<SeanceRadiotherapie> specification = new SeanceRadiotherapieSpecification(seanceRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return seanceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(seanceRadiotherapie -> new SeanceRadiotherapieDto(seanceRadiotherapie)).collect(Collectors.toList());
	}
	
	/**
	 * getSeanceRadiotherapieDataSize.
	 * 
	 * @param seanceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getSeanceRadiotherapieDataSize(SeanceRadiotherapieCriteria seanceRadiotherapieCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		seanceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<SeanceRadiotherapie> specification = new SeanceRadiotherapieSpecification(seanceRadiotherapieCriteria, true);
		
		return ((Long)  seanceRadiotherapieRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteSeanceRadiotherapie.
	 * 
	 * @param seanceRadiotherapieList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteSeanceRadiotherapie(List<SeanceRadiotherapieDto> seanceRadiotherapieList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (seanceRadiotherapieList != null)
			for (SeanceRadiotherapieDto seanceRadiotherapieDto : seanceRadiotherapieList) {
					SeanceRadiotherapie toBeDeleted = seanceRadiotherapieRepository.findById(seanceRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { SeanceRadiotherapie.class.getSimpleName(), seanceRadiotherapieDto.getId().toString() }));

					seanceRadiotherapieRepository.delete(toBeDeleted);

					HistSeanceRadiotherapie histSeanceRadiotherapie = new HistSeanceRadiotherapie();
					histSeanceRadiotherapie.setActionType(ACTION_TYPE.DELETE.name());
					histSeanceRadiotherapie.setObjectName("seanceRadiotherapie");
					histSeanceRadiotherapie.setObjectId(seanceRadiotherapieDto.getId());
					String seanceRadiotherapieValue =  new ObjectMapper().writeValueAsString(seanceRadiotherapieDto);
					histSeanceRadiotherapie.setData(seanceRadiotherapieValue);
					histSeanceRadiotherapie.setUserId(currentUser.getId());
					histSeanceRadiotherapie.setUsername(currentUser.getUsername());
					histSeanceRadiotherapie.setDate(LocalDateTime.now());
					histSeanceRadiotherapieRepository.save(histSeanceRadiotherapie);					
			}
	}
	
	

	
	private void saveSeanceRadiotherapieAuditData(SeanceRadiotherapieDto seanceRadiotherapie, ACTION_TYPE action) throws Exception {

		SeanceRadiotherapieDto oldSeanceRadiotherapie = getSeanceRadiotherapieById(seanceRadiotherapie.getId());
		if (Utils.compareObjectsDiff(seanceRadiotherapie, oldSeanceRadiotherapie)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistSeanceRadiotherapie histSeanceRadiotherapie = new HistSeanceRadiotherapie();
			histSeanceRadiotherapie.setActionType(action.name());
			histSeanceRadiotherapie.setObjectName("seanceRadiotherapie");
			histSeanceRadiotherapie.setObjectId(seanceRadiotherapie.getId());
			histSeanceRadiotherapie.setUserId(currentUser.getId());
			histSeanceRadiotherapie.setUsername(currentUser.getUsername());
			String seanceRadiotherapieValue =  new ObjectMapper().writeValueAsString(seanceRadiotherapie);
			histSeanceRadiotherapie.setData(seanceRadiotherapieValue);
			histSeanceRadiotherapie.setDate(LocalDateTime.now());
			histSeanceRadiotherapieRepository.save(histSeanceRadiotherapie);
		}
	}	
	
	/**
	 * getHistSeanceRadiotherapieById.
	 * 
	 * @param histSeanceRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistSeanceRadiotherapieById(Long histSeanceRadiotherapieId) throws Exception {

		HistSeanceRadiotherapie histSeanceRadiotherapie = histSeanceRadiotherapieRepository.findById(histSeanceRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistSeanceRadiotherapie.class.getSimpleName(), histSeanceRadiotherapieId.toString() }));

			return  new AuditEntityDto(histSeanceRadiotherapie);

	}
	

	/**
	 * paginatedListHistSeanceRadiotherapies.
	 * 
	 * @param histSeanceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistSeanceRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistSeanceRadiotherapies(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistSeanceRadiotherapie> specification = new HistSeanceRadiotherapieSpecification(histSeanceRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histSeanceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(seanceRadiotherapie -> new AuditEntityDto(seanceRadiotherapie)).collect(Collectors.toList());
	}

	/**
	 * findSeanceRadiotherapiesHistByCriteria.
	 * 
	 * @param HistSeanceRadiotherapieCriteria
	 * @return List<HistSeanceRadiotherapie>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findSeanceRadiotherapiesHistByCriteria(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception {
					 
		Specification<HistSeanceRadiotherapie> specification = new HistSeanceRadiotherapieSpecification(histSeanceRadiotherapieCriteria);
		
		if (histSeanceRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histSeanceRadiotherapieCriteria.getMaxResults());
			return histSeanceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(seanceRadiotherapie -> new AuditEntityDto(seanceRadiotherapie)).collect(Collectors.toList());

		} else {
			return histSeanceRadiotherapieRepository.findAll(specification).stream().map(seanceRadiotherapie -> new AuditEntityDto(seanceRadiotherapie)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistSeanceRadiotherapieDataSize.
	 * 
	 * @param histSeanceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistSeanceRadiotherapieDataSize(HistSeanceRadiotherapieCriteria histSeanceRadiotherapieCriteria) throws Exception {
	
		Specification<HistSeanceRadiotherapie> specification = new HistSeanceRadiotherapieSpecification(histSeanceRadiotherapieCriteria, true);
		
		return ((Long)  histSeanceRadiotherapieRepository.count(specification)).intValue();
		
	}	

}