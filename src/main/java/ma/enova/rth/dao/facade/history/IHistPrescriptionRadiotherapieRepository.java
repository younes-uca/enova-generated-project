package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistPrescriptionRadiotherapie;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistPrescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistPrescriptionRadiotherapieRepository extends AbstractHistoryRepository<HistPrescriptionRadiotherapie, Long> {


}
