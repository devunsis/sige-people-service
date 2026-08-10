package mx.edu.unsis.sige.shared.infrastructure.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Representa al usuario autenticado durante el flujo de LOGIN, cuando
 * si es necesario consultar AccountRepository para validar credenciales
 * contra la base de datos (via AccountUserDetailsService).
 *
 * Una vez emitido el JWT, las peticiones posteriores usan AuthenticatedUser
 * en su lugar, reconstruido del token sin tocar la BD.
 */
public class UserPrincipal implements UserDetails {

    private final UUID id;
    private final String username;
    private final String email;
    private final String passwordHash;
    private final boolean activo;
    private final Set<String> roles;
    private final Set<String> permisos;

    public UserPrincipal(UUID id, String username, String email,
            String passwordHash, boolean activo, Set<String> roles,
            Set<String> permisos) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.activo = activo;
        this.roles = roles;
        this.permisos = permisos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activo;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Set<String> getPermisos() {
        return permisos;
    }
}