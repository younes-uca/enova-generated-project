package ma.enova.rth.security;

import ma.enova.rth.dao.facade.core.IUtilisateurRepository;
import ma.enova.rth.domain.core.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service des authentifications
 */

@Service("userDetailsService")
public class UtilisateurDetailsServiceImpl implements UserDetailsService {
    /**
     * Manager de l'authentification
     */
    @Autowired
    private IUtilisateurRepository utilisateurRepository;

    /**
     *
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Utilisateur utilisateur = null;
        try {
            utilisateur = null;//utilisateurRepository.loadUserByUsername(username);
        } catch (Exception ex) {
            throw new DataRetrievalFailureException("Utilisateur " + username + " inconnu, " + ex.getMessage(), ex);
        }

        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur " + username + " inconnu");
        }

        return new UtilisateurDetailsImpl(utilisateur);
    }


}