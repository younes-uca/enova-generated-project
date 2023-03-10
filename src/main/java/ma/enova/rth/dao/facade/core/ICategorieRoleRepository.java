package ma.enova.rth.dao.facade.core;

import ma.enova.rth.bean.core.CategorieRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IcategorieRole
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface ICategorieRoleRepository extends JpaRepository<CategorieRole, Long>, JpaSpecificationExecutor<CategorieRole> {


}
