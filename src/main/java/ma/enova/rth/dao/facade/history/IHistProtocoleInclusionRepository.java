package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistProtocoleInclusion;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistProtocoleInclusion
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistProtocoleInclusionRepository extends AbstractHistoryRepository<HistProtocoleInclusion, Long> {


}
