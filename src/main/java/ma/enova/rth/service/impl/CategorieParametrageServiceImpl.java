package ma.enova.rth.service.impl;

import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.dao.criteria.core.CategorieParametrageCriteria;
import ma.enova.rth.dao.facade.core.ICategorieParametrageRepository;
import ma.enova.rth.dao.specifications.core.CategorieParametrageSpecification;
import ma.enova.rth.domain.core.CategorieParametrage;
import ma.enova.rth.dto.CategorieParametrageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation du service IcategorieParametrage
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "categorieParametrageService")
public class CategorieParametrageServiceImpl {

    @Autowired
    private ICategorieParametrageRepository categorieParametrageRepository;


    /**
     * createCategorieParametrage.
     *
     * @return CategorieParametrage
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieParametrageDto createCategorieParametrage(CategorieParametrageDto categorieParametrageDto) throws Exception {


        CategorieParametrage categorieParametrage = new CategorieParametrage();
        categorieParametrage = categorieParametrageDto.convertToEntity(categorieParametrage, categorieParametrageDto);
        CategorieParametrage newCategorieParametrage = categorieParametrageRepository.save(categorieParametrage);
        categorieParametrageDto.setId(newCategorieParametrage.getId());


        return categorieParametrageDto;
    }

    /**
     * updateCategorieParametrage.
     *
     * @return CategorieParametrage
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieParametrageDto updateCategorieParametrage(CategorieParametrageDto categorieParametrageDto) throws Exception {


        CategorieParametrage categorieParametrage = categorieParametrageRepository.findById(categorieParametrageDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{CategorieParametrage.class.getSimpleName(), categorieParametrageDto.getId().toString()}));
        categorieParametrage = categorieParametrageDto.convertToEntity(categorieParametrage, categorieParametrageDto);
        categorieParametrageRepository.saveAndFlush(categorieParametrage);

        return categorieParametrageDto;
    }

    /**
     * getCategorieParametrageById.
     *
     * @param categorieParametrageId
     * @return CategorieParametrage
     * @throws Exception
     */
    public CategorieParametrageDto getCategorieParametrageById(Long categorieParametrageId) throws Exception {

        CategorieParametrage categorieParametrage = categorieParametrageRepository.findById(categorieParametrageId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{CategorieParametrage.class.getSimpleName(), categorieParametrageId.toString()}));

        return new CategorieParametrageDto(categorieParametrage, true, 0);

    }


    /**
     * findCategorieParametragesByCriteria.
     *
     * @param categorieParametrageCriteria
     * @return List<CategorieParametrage>
     * @throws Exception
     */
    public List<CategorieParametrageDto> findCategorieParametragesByCriteria(CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        Specification<CategorieParametrage> specification = new CategorieParametrageSpecification(categorieParametrageCriteria);

        if (categorieParametrageCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, categorieParametrageCriteria.getMaxResults());
            return categorieParametrageRepository.findAll(specification, pageable).getContent().stream().map(categorieParametrage -> new CategorieParametrageDto(categorieParametrage)).collect(Collectors.toList());

        } else {
            return categorieParametrageRepository.findAll(specification).stream().map(categorieParametrage -> new CategorieParametrageDto(categorieParametrage)).collect(Collectors.toList());
        }

    }

    /**
     * findCategorieParametrageByCriteria.
     *
     * @param categorieParametrageCriteria
     * @return CategorieParametrage
     * @throws Exception
     */

    public CategorieParametrageDto findCategorieParametrageByCriteria(CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        Specification<CategorieParametrage> specification = new CategorieParametrageSpecification(categorieParametrageCriteria);

        CategorieParametrage categorieParametrage = categorieParametrageRepository.findOne(specification).orElse(null);

        CategorieParametrageDto categorieParametrageDto = null;
        if (categorieParametrage != null) {
            categorieParametrageDto = new CategorieParametrageDto();
            return new CategorieParametrageDto(categorieParametrage, true);
        }

        return categorieParametrageDto;
    }

    /**
     * paginatedListCategorieParametrages.
     *
     * @param categorieParametrageCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<CategorieParametrage>
     * @throws Exception
     */

    public List<CategorieParametrageDto> paginatedListCategorieParametrages(CategorieParametrageCriteria categorieParametrageCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<CategorieParametrage> specification = new CategorieParametrageSpecification(categorieParametrageCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return categorieParametrageRepository.findAll(specification, pageable).getContent().stream().map(categorieParametrage -> new CategorieParametrageDto(categorieParametrage)).collect(Collectors.toList());
    }

    /**
     * getCategorieParametrageDataSize.
     *
     * @param categorieParametrageCriteria
     * @return int
     * @throws Exception
     */
    public int getCategorieParametrageDataSize(CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        Specification<CategorieParametrage> specification = new CategorieParametrageSpecification(categorieParametrageCriteria, true);

        return ((Long) categorieParametrageRepository.count(specification)).intValue();

    }

    /**
     * deleteCategorieParametrage.
     *
     * @param categorieParametrageList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteCategorieParametrage(List<CategorieParametrageDto> categorieParametrageList) throws Exception {


        if (categorieParametrageList != null)
            for (CategorieParametrageDto categorieParametrageDto : categorieParametrageList) {
                CategorieParametrage toBeDeleted = categorieParametrageRepository.findById(categorieParametrageDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{CategorieParametrage.class.getSimpleName(), categorieParametrageDto.getId().toString()}));

                categorieParametrageRepository.delete(toBeDeleted);

            }
    }


}