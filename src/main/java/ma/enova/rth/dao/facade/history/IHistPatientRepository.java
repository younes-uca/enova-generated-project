package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.domain.historique.HistPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistPatient
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistPatientRepository extends AbstractHistoryRepository<HistPatient, Long> {


}
