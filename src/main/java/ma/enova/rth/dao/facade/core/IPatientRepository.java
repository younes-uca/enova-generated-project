package ma.enova.rth.dao.facade.core;

import ma.enova.rth.zynerator.repository.AbstractRepository;
import ma.enova.rth.bean.core.Patient;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ipatient
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IPatientRepository extends AbstractRepository<Patient, Long> {


}
