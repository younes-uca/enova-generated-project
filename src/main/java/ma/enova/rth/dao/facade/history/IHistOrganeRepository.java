package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistOrgane;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistOrgane
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistOrganeRepository extends AbstractHistoryRepository<HistOrgane, Long> {


}
