package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Visee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ivisee
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IViseeRepository extends JpaRepository<Visee	, Long> ,JpaSpecificationExecutor<Visee> {


}
