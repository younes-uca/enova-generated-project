package ma.enova.rth.dao.specfication.core;

import ma.enova.rth.dao.criteria.core.UtilisateurCriteria;
import ma.enova.rth.bean.core.Utilisateur;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


public class UtilisateurSpecification implements Specification<Utilisateur> {

    private final UtilisateurCriteria criteria;
    private boolean distinct;

    public UtilisateurSpecification(UtilisateurCriteria criteria) {

        this.criteria = criteria;
    }

    public UtilisateurSpecification(UtilisateurCriteria criteria, boolean distinct) {

        this.criteria = criteria;
        this.distinct = distinct;
    }

    @Override
    public Predicate toPredicate(Root<Utilisateur> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
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
            if (criteria.getNom() != null && !criteria.getNom().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("nom"), criteria.getNom()));
            }
            if (criteria.getNomLike() != null && !criteria.getNomLike().isEmpty()) {
                Expression<String> path = root.get("nom");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getNomLike().toLowerCase() + "%"));
            }
            if (criteria.getPrenom() != null && !criteria.getPrenom().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("prenom"), criteria.getPrenom()));
            }
            if (criteria.getPrenomLike() != null && !criteria.getPrenomLike().isEmpty()) {
                Expression<String> path = root.get("prenom");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getPrenomLike().toLowerCase() + "%"));
            }
            if (criteria.getCin() != null && !criteria.getCin().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("cin"), criteria.getCin()));
            }
            if (criteria.getCinLike() != null && !criteria.getCinLike().isEmpty()) {
                Expression<String> path = root.get("cin");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getCinLike().toLowerCase() + "%"));
            }
            if (criteria.getAdresse() != null && !criteria.getAdresse().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("adresse"), criteria.getAdresse()));
            }
            if (criteria.getAdresseLike() != null && !criteria.getAdresseLike().isEmpty()) {
                Expression<String> path = root.get("adresse");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getAdresseLike().toLowerCase() + "%"));
            }
            if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("email"), criteria.getEmail()));
            }
            if (criteria.getEmailLike() != null && !criteria.getEmailLike().isEmpty()) {
                Expression<String> path = root.get("email");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getEmailLike().toLowerCase() + "%"));
            }
            if (criteria.getTelephone() != null && !criteria.getTelephone().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("telephone"), criteria.getTelephone()));
            }
            if (criteria.getTelephoneLike() != null && !criteria.getTelephoneLike().isEmpty()) {
                Expression<String> path = root.get("telephone");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getTelephoneLike().toLowerCase() + "%"));
            }
            if (criteria.getMobile() != null && !criteria.getMobile().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("mobile"), criteria.getMobile()));
            }
            if (criteria.getMobileLike() != null && !criteria.getMobileLike().isEmpty()) {
                Expression<String> path = root.get("mobile");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getMobileLike().toLowerCase() + "%"));
            }
            if (criteria.getExpertise() != null && !criteria.getExpertise().isEmpty()) {
                predicates.add(builder.equal(root.<Boolean>get("expertise"), Boolean.valueOf(criteria.getExpertise())));
            }
            if (criteria.getUsername() != null && !criteria.getUsername().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("username"), criteria.getUsername()));
            }
            if (criteria.getUsernameLike() != null && !criteria.getUsernameLike().isEmpty()) {
                Expression<String> path = root.get("username");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getUsernameLike().toLowerCase() + "%"));
            }
            if (criteria.getPassword() != null && !criteria.getPassword().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("password"), criteria.getPassword()));
            }
            if (criteria.getPasswordLike() != null && !criteria.getPasswordLike().isEmpty()) {
                Expression<String> path = root.get("password");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getPasswordLike().toLowerCase() + "%"));
            }
            if (criteria.getEnabled() != null && !criteria.getEnabled().isEmpty()) {
                predicates.add(builder.equal(root.<Boolean>get("enabled"), Boolean.valueOf(criteria.getEnabled())));
            }
            if (criteria.getEnabledAndIds() != null && !criteria.getEnabledAndIds().isEmpty()) {
                predicates.add(builder.or(builder.equal(root.<Boolean>get("enabled"), true), root.<Long>get("id").in(criteria.getEnabledAndIds())));
            }
            if (criteria.getResetPassword() != null && !criteria.getResetPassword().isEmpty()) {
                predicates.add(builder.equal(root.<Boolean>get("resetPassword"), Boolean.valueOf(criteria.getResetPassword())));
            }
            if (criteria.getOldPassword() != null && !criteria.getOldPassword().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("oldPassword"), criteria.getOldPassword()));
            }
            if (criteria.getOldPasswordLike() != null && !criteria.getOldPasswordLike().isEmpty()) {
                Expression<String> path = root.get("oldPassword");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getOldPasswordLike().toLowerCase() + "%"));
            }
            if (criteria.getNewPassword() != null && !criteria.getNewPassword().isEmpty()) {
                predicates.add(builder.equal(root.<String>get("newPassword"), criteria.getNewPassword()));
            }
            if (criteria.getNewPasswordLike() != null && !criteria.getNewPasswordLike().isEmpty()) {
                Expression<String> path = root.get("newPassword");
                Expression<String> lower = builder.lower(path);
                predicates.add(builder.like(lower, "%" + criteria.getNewPasswordLike().toLowerCase() + "%"));
            }
            if (criteria.getEtablissementId() != null && criteria.getEtablissementId() > 0) {
                predicates.add(builder.equal(root.<Long>get("etablissement"), criteria.getEtablissementId()));
            }

            if (criteria.getProfilId() != null && criteria.getProfilId() > 0) {
                predicates.add(builder.equal(root.<Long>get("profil"), criteria.getProfilId()));
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