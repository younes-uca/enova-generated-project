package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistOrgane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistOrgane
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistOrganeRepository extends JpaRepository<HistOrgane	, Long> ,JpaSpecificationExecutor<HistOrgane> {


}
