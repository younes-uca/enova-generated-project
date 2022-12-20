package ma.enova.rth.common.util;

import ma.enova.rth.security.UtilisateurDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    private UserUtil(){}
    public static Long getCurrentUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return currentUser.getId();
        }
        return 0l;
    }

    public static String getCurrentUserName() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return currentUser.getNomComplet();
        }
        return null;
    }

}
