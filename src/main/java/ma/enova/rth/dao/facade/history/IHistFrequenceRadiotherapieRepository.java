package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistFrequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistFrequenceRadiotherapieRepository extends JpaRepository<HistFrequenceRadiotherapie	, Long> ,JpaSpecificationExecutor<HistFrequenceRadiotherapie> {


}
