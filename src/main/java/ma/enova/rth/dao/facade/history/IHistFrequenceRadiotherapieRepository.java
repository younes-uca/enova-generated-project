package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistFrequenceRadiotherapie;
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
