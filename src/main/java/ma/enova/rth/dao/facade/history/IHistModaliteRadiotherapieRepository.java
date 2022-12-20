package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistModaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistModaliteRadiotherapieRepository   extends AbstractHistoryRepository<HistModaliteRadiotherapie, Long> {

}
