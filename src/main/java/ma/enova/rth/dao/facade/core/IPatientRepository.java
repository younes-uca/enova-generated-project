package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Ipatient
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IPatientRepository extends JpaRepository<Patient	, Long> ,JpaSpecificationExecutor<Patient> {


}
