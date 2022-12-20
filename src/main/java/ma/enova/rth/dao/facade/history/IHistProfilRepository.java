package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistProfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistProfil
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistProfilRepository extends JpaRepository<HistProfil	, Long> ,JpaSpecificationExecutor<HistProfil> {


}
