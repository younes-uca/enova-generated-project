package ma.enova.rth.dao.facade.core;

import ma.enova.rth.domain.core.Utilisateur;

/**
 * Dao de l'authentification
 */
public interface UtilisateurDetailsDao {
	/**
	 * 
	 * @param username
	 * @return Utilisateur
	 * @throws Exception
	 */
	public Utilisateur loadUserByUsername(String username) throws Exception;
}
