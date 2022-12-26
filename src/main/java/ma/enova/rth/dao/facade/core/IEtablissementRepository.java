package ma.enova.rth.dao.facade.core;

import ma.enova.rth.bean.core.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ietablissement
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IEtablissementRepository extends JpaRepository<Etablissement, Long>, JpaSpecificationExecutor<Etablissement> {


}
