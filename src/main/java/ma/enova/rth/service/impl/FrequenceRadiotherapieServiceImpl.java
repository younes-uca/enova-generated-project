package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistFrequenceRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.FrequenceRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistFrequenceRadiotherapieSpecification;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
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
 * Implementation du service IfrequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "frequenceRadiotherapieService")
public class FrequenceRadiotherapieServiceImpl implements IFrequenceRadiotherapieService {

	@Autowired
	private IFrequenceRadiotherapieRepository frequenceRadiotherapieRepository;
	
	@Autowired
	private IHistFrequenceRadiotherapieRepository histFrequenceRadiotherapieRepository;



	/**
	 * createFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapie
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public FrequenceRadiotherapieDto createFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			frequenceRadiotherapieDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		FrequenceRadiotherapie frequenceRadiotherapie = new FrequenceRadiotherapie();
		frequenceRadiotherapie = frequenceRadiotherapieDto.convertToEntity(frequenceRadiotherapie, frequenceRadiotherapieDto);
		FrequenceRadiotherapie newFrequenceRadiotherapie = frequenceRadiotherapieRepository.save(frequenceRadiotherapie);
		frequenceRadiotherapieDto.setId(newFrequenceRadiotherapie.getId());		



			
		return frequenceRadiotherapieDto;
	}
	
	/**
	 * updateFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapie
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public FrequenceRadiotherapieDto updateFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		saveFrequenceRadiotherapieAuditData(frequenceRadiotherapieDto, ACTION_TYPE.UPDATE);

		FrequenceRadiotherapie frequenceRadiotherapie = frequenceRadiotherapieRepository.findById(frequenceRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { FrequenceRadiotherapie.class.getSimpleName(), frequenceRadiotherapieDto.getId().toString() }));
		frequenceRadiotherapie = frequenceRadiotherapieDto.convertToEntity(frequenceRadiotherapie, frequenceRadiotherapieDto);
		frequenceRadiotherapieRepository.saveAndFlush(frequenceRadiotherapie);
		
		return frequenceRadiotherapieDto;
	}
	
	/**
	 * getFrequenceRadiotherapieById.
	 * 
	 * @param frequenceRadiotherapieId
	 * @return FrequenceRadiotherapie
	 * @throws Exception
	 */
	public FrequenceRadiotherapieDto getFrequenceRadiotherapieById(Long frequenceRadiotherapieId) throws Exception {

		FrequenceRadiotherapie frequenceRadiotherapie = frequenceRadiotherapieRepository.findById(frequenceRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { FrequenceRadiotherapie.class.getSimpleName(), frequenceRadiotherapieId.toString() }));

		return  new FrequenceRadiotherapieDto(frequenceRadiotherapie, true, 0);

	}
	

	/**
	 * findFrequenceRadiotherapiesByCriteria.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return List<FrequenceRadiotherapie>
	 * @throws Exception
	 */	
	public List<FrequenceRadiotherapieDto> findFrequenceRadiotherapiesByCriteria(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		frequenceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<FrequenceRadiotherapie> specification = new FrequenceRadiotherapieSpecification(frequenceRadiotherapieCriteria);
		
		if (frequenceRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, frequenceRadiotherapieCriteria.getMaxResults());
			return frequenceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(frequenceRadiotherapie -> new FrequenceRadiotherapieDto(frequenceRadiotherapie)).collect(Collectors.toList());

		} else {
			return frequenceRadiotherapieRepository.findAll(specification).stream().map(frequenceRadiotherapie -> new FrequenceRadiotherapieDto(frequenceRadiotherapie)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findFrequenceRadiotherapieByCriteria.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return FrequenceRadiotherapie 
	 * @throws Exception
	 */
	
	public FrequenceRadiotherapieDto findFrequenceRadiotherapieByCriteria(FrequenceRadiotherapieCriteria  frequenceRadiotherapieCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		frequenceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<FrequenceRadiotherapie> specification = new FrequenceRadiotherapieSpecification(frequenceRadiotherapieCriteria);
		
		FrequenceRadiotherapie frequenceRadiotherapie = frequenceRadiotherapieRepository.findOne(specification).orElse(null);
		
		FrequenceRadiotherapieDto frequenceRadiotherapieDto = null;
		if (frequenceRadiotherapie != null) {
			frequenceRadiotherapieDto = new FrequenceRadiotherapieDto();
			return new FrequenceRadiotherapieDto(frequenceRadiotherapie, true);
		}

		return frequenceRadiotherapieDto;
	}

	/**
	 * paginatedListFrequenceRadiotherapies.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<FrequenceRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<FrequenceRadiotherapieDto> paginatedListFrequenceRadiotherapies(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		frequenceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<FrequenceRadiotherapie> specification = new FrequenceRadiotherapieSpecification(frequenceRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return frequenceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(frequenceRadiotherapie -> new FrequenceRadiotherapieDto(frequenceRadiotherapie)).collect(Collectors.toList());
	}
	
	/**
	 * getFrequenceRadiotherapieDataSize.
	 * 
	 * @param frequenceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getFrequenceRadiotherapieDataSize(FrequenceRadiotherapieCriteria frequenceRadiotherapieCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		frequenceRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<FrequenceRadiotherapie> specification = new FrequenceRadiotherapieSpecification(frequenceRadiotherapieCriteria, true);
		
		return ((Long)  frequenceRadiotherapieRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteFrequenceRadiotherapie.
	 * 
	 * @param frequenceRadiotherapieList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteFrequenceRadiotherapie(List<FrequenceRadiotherapieDto> frequenceRadiotherapieList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (frequenceRadiotherapieList != null)
			for (FrequenceRadiotherapieDto frequenceRadiotherapieDto : frequenceRadiotherapieList) {
					FrequenceRadiotherapie toBeDeleted = frequenceRadiotherapieRepository.findById(frequenceRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { FrequenceRadiotherapie.class.getSimpleName(), frequenceRadiotherapieDto.getId().toString() }));

					frequenceRadiotherapieRepository.delete(toBeDeleted);

					HistFrequenceRadiotherapie histFrequenceRadiotherapie = new HistFrequenceRadiotherapie();
					histFrequenceRadiotherapie.setActionType(ACTION_TYPE.DELETE.name());
					histFrequenceRadiotherapie.setObjectName("frequenceRadiotherapie");
					histFrequenceRadiotherapie.setObjectId(frequenceRadiotherapieDto.getId());
					String frequenceRadiotherapieValue =  new ObjectMapper().writeValueAsString(frequenceRadiotherapieDto);
					histFrequenceRadiotherapie.setData(frequenceRadiotherapieValue);
					histFrequenceRadiotherapie.setUserId(currentUser.getId());
					histFrequenceRadiotherapie.setUsername(currentUser.getUsername());
					histFrequenceRadiotherapie.setDate(LocalDateTime.now());
					histFrequenceRadiotherapieRepository.save(histFrequenceRadiotherapie);					
			}
	}
	
	

	
	private void saveFrequenceRadiotherapieAuditData(FrequenceRadiotherapieDto frequenceRadiotherapie, ACTION_TYPE action) throws Exception {

		FrequenceRadiotherapieDto oldFrequenceRadiotherapie = getFrequenceRadiotherapieById(frequenceRadiotherapie.getId());
		if (Utils.compareObjectsDiff(frequenceRadiotherapie, oldFrequenceRadiotherapie)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistFrequenceRadiotherapie histFrequenceRadiotherapie = new HistFrequenceRadiotherapie();
			histFrequenceRadiotherapie.setActionType(action.name());
			histFrequenceRadiotherapie.setObjectName("frequenceRadiotherapie");
			histFrequenceRadiotherapie.setObjectId(frequenceRadiotherapie.getId());
			histFrequenceRadiotherapie.setUserId(currentUser.getId());
			histFrequenceRadiotherapie.setUsername(currentUser.getUsername());
			String frequenceRadiotherapieValue =  new ObjectMapper().writeValueAsString(frequenceRadiotherapie);
			histFrequenceRadiotherapie.setData(frequenceRadiotherapieValue);
			histFrequenceRadiotherapie.setDate(LocalDateTime.now());
			histFrequenceRadiotherapieRepository.save(histFrequenceRadiotherapie);
		}
	}	
	
	/**
	 * getHistFrequenceRadiotherapieById.
	 * 
	 * @param histFrequenceRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistFrequenceRadiotherapieById(Long histFrequenceRadiotherapieId) throws Exception {

		HistFrequenceRadiotherapie histFrequenceRadiotherapie = histFrequenceRadiotherapieRepository.findById(histFrequenceRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistFrequenceRadiotherapie.class.getSimpleName(), histFrequenceRadiotherapieId.toString() }));

			return  new AuditEntityDto(histFrequenceRadiotherapie);

	}
	

	/**
	 * paginatedListHistFrequenceRadiotherapies.
	 * 
	 * @param histFrequenceRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistFrequenceRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistFrequenceRadiotherapies(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistFrequenceRadiotherapie> specification = new HistFrequenceRadiotherapieSpecification(histFrequenceRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histFrequenceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(frequenceRadiotherapie -> new AuditEntityDto(frequenceRadiotherapie)).collect(Collectors.toList());
	}

	/**
	 * findFrequenceRadiotherapiesHistByCriteria.
	 * 
	 * @param HistFrequenceRadiotherapieCriteria
	 * @return List<HistFrequenceRadiotherapie>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findFrequenceRadiotherapiesHistByCriteria(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {
					 
		Specification<HistFrequenceRadiotherapie> specification = new HistFrequenceRadiotherapieSpecification(histFrequenceRadiotherapieCriteria);
		
		if (histFrequenceRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histFrequenceRadiotherapieCriteria.getMaxResults());
			return histFrequenceRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(frequenceRadiotherapie -> new AuditEntityDto(frequenceRadiotherapie)).collect(Collectors.toList());

		} else {
			return histFrequenceRadiotherapieRepository.findAll(specification).stream().map(frequenceRadiotherapie -> new AuditEntityDto(frequenceRadiotherapie)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistFrequenceRadiotherapieDataSize.
	 * 
	 * @param histFrequenceRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistFrequenceRadiotherapieDataSize(HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {
	
		Specification<HistFrequenceRadiotherapie> specification = new HistFrequenceRadiotherapieSpecification(histFrequenceRadiotherapieCriteria, true);
		
		return ((Long)  histFrequenceRadiotherapieRepository.count(specification)).intValue();
		
	}	

}