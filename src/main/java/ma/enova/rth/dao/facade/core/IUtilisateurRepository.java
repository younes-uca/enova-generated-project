package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface du DAO Iutilisateur
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IUtilisateurRepository extends JpaRepository<Utilisateur	, Long> ,JpaSpecificationExecutor<Utilisateur> {


}
