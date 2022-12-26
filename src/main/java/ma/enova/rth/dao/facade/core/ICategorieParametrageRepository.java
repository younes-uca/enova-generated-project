package ma.enova.rth.dao.facade.core;

import ma.enova.rth.bean.core.CategorieParametrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IcategorieParametrage
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface ICategorieParametrageRepository extends JpaRepository<CategorieParametrage, Long>, JpaSpecificationExecutor<CategorieParametrage> {

}
