package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.domain.historique.HistProtocoleInclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistProtocoleInclusion
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistProtocoleInclusionRepository extends AbstractHistoryRepository<HistProtocoleInclusion, Long> {


}
