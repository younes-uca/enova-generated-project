package ma.enova.rth.dao.facade.core;

import ma.enova.rth.common.ddd.repository.AbstractRepository;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO IseanceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface ISeanceRadiotherapieRepository extends AbstractRepository<SeanceRadiotherapie, Long> {


}
