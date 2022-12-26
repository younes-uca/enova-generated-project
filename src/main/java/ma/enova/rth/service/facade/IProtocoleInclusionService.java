package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.bean.core.ProtocoleInclusion;
import ma.enova.rth.dto.ProtocoleInclusionDto;

/**
 * Interface service protocoleInclusion
 *
 * @author JAF
 * @version 1.2
 */
public interface IProtocoleInclusionService extends IService<ProtocoleInclusion, ProtocoleInclusionDto, ProtocoleInclusionCriteria, HistProtocoleInclusionCriteria> {
}