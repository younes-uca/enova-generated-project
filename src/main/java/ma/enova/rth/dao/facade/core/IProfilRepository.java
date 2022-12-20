package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO Iprofil
 *
 * @author JAF
 * @version 1.2
 */

@Repository
public interface IProfilRepository extends JpaRepository<Profil, Long>, JpaSpecificationExecutor<Profil> {


}
