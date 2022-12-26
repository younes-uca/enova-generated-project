package ma.enova.rth.dao.facade.history;

import ma.enova.rth.zynerator.repository.AbstractHistoryRepository;
import ma.enova.rth.bean.historique.HistPatient;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistPatient
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IHistPatientRepository extends AbstractHistoryRepository<HistPatient, Long> {


}
