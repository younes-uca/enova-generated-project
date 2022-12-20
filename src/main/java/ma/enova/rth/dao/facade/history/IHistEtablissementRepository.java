package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistEtablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistEtablissement
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistEtablissementRepository extends JpaRepository<HistEtablissement, Long>, JpaSpecificationExecutor<HistEtablissement> {


}
