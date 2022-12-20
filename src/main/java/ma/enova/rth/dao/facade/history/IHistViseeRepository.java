package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistVisee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistVisee
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistViseeRepository extends JpaRepository<HistVisee	, Long> ,JpaSpecificationExecutor<HistVisee> {


}
