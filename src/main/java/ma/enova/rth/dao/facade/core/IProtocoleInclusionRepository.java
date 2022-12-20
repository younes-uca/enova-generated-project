package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.ProtocoleInclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IprotocoleInclusion
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IProtocoleInclusionRepository extends JpaRepository<ProtocoleInclusion	, Long> ,JpaSpecificationExecutor<ProtocoleInclusion> {


}
