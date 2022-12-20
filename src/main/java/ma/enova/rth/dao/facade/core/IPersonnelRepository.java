package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Personnel;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ipersonnel
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IPersonnelRepository extends AbstractRepository<Personnel, Long> {


}
