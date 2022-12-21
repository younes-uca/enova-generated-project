package ma.enova.rth.dao.specfication.history;

import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.domain.historique.HistPatient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class HistPatientSpecification implements Specification<HistPatient> {

    private final HistPatientCriteria criteria;
    private boolean distinct;

    public HistPatientSpecification(HistPatientCriteria criteria) {

        this.criteria = criteria;
    }

    public HistPatientSpecification(HistPatientCriteria criteria, boolean distinct) {

        this.criteria = criteria;
        this.distinct = distinct;
    }

    @Override
    public Predicate toPredicate(Root<HistPatient> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
            if (criteria.getObjectName() != null && !criteria.getObjectName().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("objectName"), criteria.getObjectName()));
            }
            if (criteria.getObjectNameLike() != null && !criteria.getObjectNameLike().isEmpty()) {
                Expression<String> path = root.get("objectName");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getObjectNameLike().toLowerCase() + "%"));
            }
            if (criteria.getData() != null && !criteria.getData().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("data"), criteria.getData()));
            }
            if (criteria.getDataLike() != null && !criteria.getDataLike().isEmpty()) {
                Expression<String> path = root.get("data");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getDataLike().toLowerCase() + "%"));
            }
            if (criteria.getUserId() != null && !criteria.getUserId().isEmpty()) {
                predicates.add(builder.equal(root.<Long>get("userId"), Long.parseLong(criteria.getUserId())));
            }
            if (criteria.getUsername() != null && !criteria.getUsername().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("username"), criteria.getUsername()));
            }
            if (criteria.getUsernameLike() != null && !criteria.getUsernameLike().isEmpty()) {
                Expression<String> path = root.get("username");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getUsernameLike().toLowerCase() + "%"));
            }
            if (criteria.getActionType() != null && !criteria.getActionType().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("actionType"), criteria.getActionType()));
            }
            if (criteria.getActionTypeLike() != null && !criteria.getActionTypeLike().isEmpty()) {
                Expression<String> path = root.get("actionType");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getActionTypeLike().toLowerCase() + "%"));
            }
            if (criteria.getObjectId() != null && !criteria.getObjectId().isEmpty()) {
                predicates.add(builder.equal(root.<Long>get("objectId"), Long.parseLong(criteria.getObjectId())));
            }
            if (criteria.getDate() != null) {
                predicates.add(builder.equal(root.<LocalDateTime>get("date"), criteria.getDate()));
            }
            if (criteria.getDateFrom() != null && criteria.getDateTo() != null) {
                predicates.add(builder.between(root.get("date"), criteria.getDateFrom(), criteria.getDateTo()));
            } else if (criteria.getDateFrom() != null) {
                predicates.add(builder.greaterThan(root.get("date"), criteria.getDateFrom()));
            } else if (criteria.getDateTo() != null) {
                predicates.add(builder.lessThan(root.get("date"), criteria.getDateTo()));
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