package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistModaliteRadiotherapie;
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
