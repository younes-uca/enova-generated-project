package ma.enova.rth.dao.facade.core;

import ma.enova.rth.zynerator.repository.AbstractRepository;
import ma.enova.rth.bean.core.Organe;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Iorgane
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IOrganeRepository extends AbstractRepository<Organe, Long> {


}
