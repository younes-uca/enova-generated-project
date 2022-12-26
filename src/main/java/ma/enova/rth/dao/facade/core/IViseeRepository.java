package ma.enova.rth.dao.facade.core;

import ma.enova.rth.zynerator.repository.AbstractRepository;
import ma.enova.rth.bean.core.Visee;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ivisee
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IViseeRepository extends AbstractRepository<Visee, Long> {


}
