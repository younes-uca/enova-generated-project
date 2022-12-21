package ma.enova.rth.dao.facade.history;

import ma.enova.rth.common.ddd.repository.AbstractHistoryRepository;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistSeanceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistSeanceRadiotherapieRepository extends AbstractHistoryRepository<HistSeanceRadiotherapie, Long> {


}
