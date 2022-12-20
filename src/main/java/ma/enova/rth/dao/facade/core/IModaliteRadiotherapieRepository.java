package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Interface du DAO ImodaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */
 
@Repository 
public interface IModaliteRadiotherapieRepository extends JpaRepository<ModaliteRadiotherapie	, Long> ,JpaSpecificationExecutor<ModaliteRadiotherapie> {


}
