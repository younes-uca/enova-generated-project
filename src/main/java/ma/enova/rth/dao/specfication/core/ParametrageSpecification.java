package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.common.enumeration.TYPE_VALEUR;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ParametrageCriteria;
import ma.enova.rth.domain.core.Parametrage;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class ParametrageSpecification implements Specification<Parametrage> {

    private final ParametrageCriteria criteria;
    private boolean distinct;

    public ParametrageSpecification(ParametrageCriteria criteria) {

        this.criteria = criteria;
    }

    public ParametrageSpecification(ParametrageCriteria criteria, boolean distinct) {

        this.criteria = criteria;
        this.distinct = distinct;
    }

    @Override
    public Predicate toPredicate(Root<Parametrage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (criteria != null) {
            if (criteria.getId() != null && criteria.getId() > 0) {
                predicates.add(builder.equal(root.<String>get("id"), criteria.getId()));
            }
            if (criteria.getIdsIn() != null && !criteria.getIdsIn().isEmpty()) {
                predicates.add(root.<Long>get("id").in(criteria.getIdsIn()));
            }
            if (criteria.getIdsNotIn() != null && !criteria.getIdsNotIn().isEmpty()) {
                predicates.add(builder.not(root.<Long>get("id").in(criteria.getIdsNotIn())));
            }
            if (criteria.getNotId() != null && criteria.getNotId() > 0) {
                predicates.add(builder.notEqual(root.<Boolean>get("id"), criteria.getNotId()));
            }
            if (criteria.getCode() != null && !criteria.getCode().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("code"), criteria.getCode()));
            }
            if (criteria.getCodeLike() != null && !criteria.getCodeLike().isEmpty()) {
                Expression<String> path = root.get("code");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getCodeLike().toLowerCase() + "%"));
            }
            if (criteria.getValeur() != null && !criteria.getValeur().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("valeur"), criteria.getValeur()));
            }
            if (criteria.getValeurLike() != null && !criteria.getValeurLike().isEmpty()) {
                Expression<String> path = root.get("valeur");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getValeurLike().toLowerCase() + "%"));
            }
            if (StringUtil.isNotEmpty(criteria.getTypeValeur())) {
                predicates.add(builder.equal(root.<TYPE_VALEUR>get("typeValeur"), TYPE_VALEUR.valueOf(criteria.getTypeValeur())));
            }
            if (criteria.getTypeValeurIn() != null && !criteria.getTypeValeurIn().isEmpty()) {
                predicates.add(root.<TYPE_VALEUR>get("typeValeur").in(criteria.getTypeValeurIn()));
            }
            if (criteria.getTypeValeurNotIn() != null && !criteria.getTypeValeurNotIn().isEmpty()) {
                predicates.add(builder.not(root.<TYPE_VALEUR>get("typeValeur").in(criteria.getTypeValeurNotIn())));
            }
            if (criteria.getDescription() != null && !criteria.getDescription().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("description"), criteria.getDescription()));
            }
            if (criteria.getDescriptionLike() != null && !criteria.getDescriptionLike().isEmpty()) {
                Expression<String> path = root.get("description");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getDescriptionLike().toLowerCase() + "%"));
            }
            if (criteria.getCategorieRoleId() != null && criteria.getCategorieRoleId() > 0) {
                predicates.add(builder.equal(root.<Long>get("categorieRole"), criteria.getCategorieRoleId()));
            }

            if (criteria.getEtablissementId() != null && criteria.getEtablissementId() > 0) {
                predicates.add(builder.equal(root.<Long>get("etablissement"), criteria.getEtablissementId()));
            }

            if (criteria.getFilterName() != null && !criteria.getFilterName().isEmpty() && criteria.getFilterWord() != null && !criteria.getFilterWord().isEmpty()) {
                Expression<String> path = root.get(criteria.getFilterName());
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getFilterWord().toLowerCase() + "%"));
            }
            if (criteria.getOrderByAsc() != null && criteria.getOrderByAsc().length > 0) {
                List<Order> orderList = new ArrayList<Order>();
                for (int i = 0; i < criteria.getOrderByAsc().length; i++) {
                    orderList.add(builder.asc(root.get(criteria.getOrderByAsc()[i])));
                }
                query.orderBy(orderList);
            }
            if (criteria.getOrderByDesc() != null && criteria.getOrderByDesc().length > 0) {
                List<Order> orderList = new ArrayList<Order>();
                for (int i = 0; i < criteria.getOrderByDesc().length; i++) {
                    orderList.add(builder.desc(root.get(criteria.getOrderByDesc()[i])));
                }
                query.orderBy(orderList);
            }
        }
        if (distinct)
            query.distinct(true);
        return andTogether(predicates, builder);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}