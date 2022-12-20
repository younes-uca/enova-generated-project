package ma.enova.rth.security;

import ma.enova.rth.domain.core.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class GrantedAuthorityImpl implements Serializable, GrantedAuthority {

    private final Role role;

    public GrantedAuthorityImpl() {
        this.role = new Role();
    }

    public GrantedAuthorityImpl(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getLibelle();
    }

}
