package ma.enova.rth.dao.facade.history;

import ma.enova.rth.bean.historique.HistCategorieRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistCategorieRole
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistCategorieRoleRepository extends JpaRepository<HistCategorieRole, Long>, JpaSpecificationExecutor<HistCategorieRole> {


}
