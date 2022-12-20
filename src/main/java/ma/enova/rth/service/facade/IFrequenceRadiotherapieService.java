package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.service.core.IService;

import java.util.List;
/**
 * Interface service frequenceRadiotherapie
 * @author JAF
 * @version 1.2
 */
public interface IFrequenceRadiotherapieService extends IService<FrequenceRadiotherapie, FrequenceRadiotherapieDto, FrequenceRadiotherapieCriteria, HistFrequenceRadiotherapieCriteria> {
}