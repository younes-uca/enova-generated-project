package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistSeanceRadiotherapie;
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
