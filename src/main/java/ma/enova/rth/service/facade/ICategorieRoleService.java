package ma.enova.rth.service.facade;

import ma.enova.rth.zynerator.service.IService;
import ma.enova.rth.dao.criteria.core.CategorieRoleCriteria;
import ma.enova.rth.dao.criteria.history.HistCategorieRoleCriteria;
import ma.enova.rth.bean.core.CategorieRole;
import ma.enova.rth.dto.CategorieRoleDto;

/**
 * Interface service categorieRole
 *
 * @author JAF
 * @version 1.2
 */
public interface ICategorieRoleService extends IService<CategorieRole, CategorieRoleDto, CategorieRoleCriteria, HistCategorieRoleCriteria> {


}
