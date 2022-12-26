package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistVisee;
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
