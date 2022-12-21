package ma.enova.rth.dao.facade.history;

import ma.enova.rth.common.ddd.repository.AbstractHistoryRepository;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistFrequenceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistFrequenceRadiotherapieRepository extends AbstractHistoryRepository<HistFrequenceRadiotherapie, Long> {


}
