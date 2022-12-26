package ma.enova.rth.dao.facade.history;

import ma.enova.rth.bean.historique.HistUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistUtilisateur
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistUtilisateurRepository extends JpaRepository<HistUtilisateur, Long>, JpaSpecificationExecutor<HistUtilisateur> {


}
