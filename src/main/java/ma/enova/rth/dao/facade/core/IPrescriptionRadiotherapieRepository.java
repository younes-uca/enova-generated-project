package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IprescriptionRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IPrescriptionRadiotherapieRepository extends AbstractRepository<PrescriptionRadiotherapie, Long>  {


}
