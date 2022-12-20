package ma.enova.rth.service.facade;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.dao.criteria.core.CategorieRoleCriteria;
import ma.enova.rth.dao.criteria.history.HistCategorieRoleCriteria;
import ma.enova.rth.domain.core.CategorieRole;
import ma.enova.rth.dto.CategorieRoleDto;
import ma.enova.rth.service.core.IService;

import java.util.List;

/**
 * Interface service categorieRole
 *
 * @author JAF
 * @version 1.2
 */
public interface ICategorieRoleService extends IService<CategorieRole, CategorieRoleDto, CategorieRoleCriteria, HistCategorieRoleCriteria> {


}
