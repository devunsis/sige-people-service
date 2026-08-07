package mx.edu.unsis.sige.shared.infrastructure.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.unsis.sige.shared.infrastructure.security.JwtProvider;
import mx.edu.unsis.sige.shared.infrastructure.security.model.AuthenticatedUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String PREFIJO_BEARER = "Bearer ";
    private final JwtProvider jwtProvider;

    // Cero consultas a BD por peticion
    public JwtAuthenticationFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraerToken(request);

        if (token != null) {
            Claims claims = jwtProvider.extraerClaims(token);

            if (claims != null) {
                autenticarEnContexto(claims, request);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extraerToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIJO_BEARER)) {
            return authorizationHeader.substring(PREFIJO_BEARER.length());
        }
        return null;
    }

    private void autenticarEnContexto(Claims claims, HttpServletRequest request) {
        AuthenticatedUser authenticatedUser = construirUsuarioDesdeClaims(claims);
        List<GrantedAuthority> authorities = construirAuthorities(authenticatedUser);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                authenticatedUser, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource()
                .buildDetails(request));

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }
    }

    @SuppressWarnings("unchecked")
    private AuthenticatedUser construirUsuarioDesdeClaims(Claims claims) {
        String subject = claims.getSubject();

        if (subject == null) {
            throw new JwtException("JWT sin subject");
        }

        List<String> roles = claims.get("roles", List.class);
        List<String> permisos = claims.get("permisos", List.class);

        return new AuthenticatedUser(
                UUID.fromString(subject),
                claims.get("username", String.class),
                claims.get("email", String.class),
                roles == null ? Set.of() : Set.copyOf(roles),
                permisos == null ? Set.of() : Set.copyOf(permisos));
    }

    private List<GrantedAuthority> construirAuthorities(AuthenticatedUser authenticatedUser) {
        List<GrantedAuthority> authorities = authenticatedUser.roles().stream()
                .map(rol -> (GrantedAuthority) new SimpleGrantedAuthority("ROLE_" + rol))
                .collect(Collectors.toCollection(java.util.ArrayList::new));

        authenticatedUser.permisos().forEach(permiso -> authorities.add(new SimpleGrantedAuthority(permiso)));

        return authorities;
    }
}