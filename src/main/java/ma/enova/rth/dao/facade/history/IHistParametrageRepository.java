package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistParametrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistParametrage
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistParametrageRepository extends JpaRepository<HistParametrage	, Long> ,JpaSpecificationExecutor<HistParametrage> {


}
