package ma.enova.rth.dao.facade.history;

import ma.enova.rth.common.ddd.repository.AbstractHistoryRepository;
import ma.enova.rth.domain.historique.HistVisee;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistVisee
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistViseeRepository extends AbstractHistoryRepository<HistVisee, Long> {


}
