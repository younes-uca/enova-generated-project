package ma.enova.rth.dao.facade.history;

import ma.enova.rth.common.ddd.repository.AbstractHistoryRepository;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistModaliteRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistModaliteRadiotherapieRepository extends AbstractHistoryRepository<HistModaliteRadiotherapie, Long> {

}
