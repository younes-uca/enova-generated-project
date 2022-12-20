package ma.enova.rth.dao.facade.history;

import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IhistSeanceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IHistSeanceRadiotherapieRepository extends JpaRepository<HistSeanceRadiotherapie	, Long> ,JpaSpecificationExecutor<HistSeanceRadiotherapie> {


}
