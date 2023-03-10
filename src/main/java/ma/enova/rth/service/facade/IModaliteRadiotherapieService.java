package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.bean.core.ModaliteRadiotherapie;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;

/**
 * Interface service modaliteRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */
public interface IModaliteRadiotherapieService extends IService<ModaliteRadiotherapie, ModaliteRadiotherapieDto, ModaliteRadiotherapieCriteria, HistModaliteRadiotherapieCriteria> {
}