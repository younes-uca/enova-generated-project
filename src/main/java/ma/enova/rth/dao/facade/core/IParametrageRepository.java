package ma.enova.rth.dao.facade.core;

import ma.enova.rth.bean.core.Parametrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Iparametrage
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IParametrageRepository extends JpaRepository<Parametrage, Long>, JpaSpecificationExecutor<Parametrage> {


}
