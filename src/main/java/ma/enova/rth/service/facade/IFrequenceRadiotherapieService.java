package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.bean.core.FrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;

/**
 * Interface service frequenceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */
public interface IFrequenceRadiotherapieService extends IService<FrequenceRadiotherapie, FrequenceRadiotherapieDto, FrequenceRadiotherapieCriteria, HistFrequenceRadiotherapieCriteria> {
    FrequenceRadiotherapieDto findByCode(String code);
}