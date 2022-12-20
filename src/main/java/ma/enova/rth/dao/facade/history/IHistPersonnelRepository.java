package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistPersonnel;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistPersonnel
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistPersonnelRepository extends AbstractHistoryRepository<HistPersonnel, Long> {


}
