package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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
