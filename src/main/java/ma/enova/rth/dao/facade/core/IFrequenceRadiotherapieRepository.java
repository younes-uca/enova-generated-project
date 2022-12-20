package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IfrequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IFrequenceRadiotherapieRepository extends JpaRepository<FrequenceRadiotherapie	, Long> ,JpaSpecificationExecutor<FrequenceRadiotherapie> {


}
