package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Irole
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IRoleRepository extends JpaRepository<Role	, Long> ,JpaSpecificationExecutor<Role> {


}
