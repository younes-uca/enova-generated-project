package ma.enova.rth.dao.facade.core;

import ma.enova.rth.bean.core.Utilisateur;

/**
 * Dao de l'authentification
 */
public interface UtilisateurDetailsDao {
    /**
     * @param username
     * @return Utilisateur
     * @throws Exception
     */
    Utilisateur loadUserByUsername(String username) throws Exception;
}
