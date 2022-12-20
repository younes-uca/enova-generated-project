package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.service.core.IService;

/**
 * Interface service protocoleInclusion
 *
 * @author JAF
 * @version 1.2
 */
public interface IProtocoleInclusionService extends IService<ProtocoleInclusion, ProtocoleInclusionDto, ProtocoleInclusionCriteria, HistProtocoleInclusionCriteria> {
}