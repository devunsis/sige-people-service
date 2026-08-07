package mx.edu.unsis.sige.shared.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import mx.edu.unsis.sige.shared.infrastructure.security.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    private final SecretKey clave;
    private final long expiracionMinutos;

    public JwtProvider(@Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-minutes:60}") long expiracionMinutos) {
        this.clave = Keys.hmacShaKeyFor(
        secret.getBytes(StandardCharsets.UTF_8));
        this.expiracionMinutos = expiracionMinutos;
    }

    public String generarToken(UserPrincipal userPrincipal) {
        Instant ahora = Instant.now();
        
        return Jwts.builder()
                .subject(userPrincipal.getId().toString())
                .issuer("sige-auth-service")
                .claim("username", userPrincipal.getUsername())
                .claim("email", userPrincipal.getEmail())
                .claim("roles", userPrincipal.getRoles())
                .claim("permisos", userPrincipal.getPermisos())
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(ahora.plus(expiracionMinutos, ChronoUnit.MINUTES)))
                .signWith(clave)
                .compact();
    }

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    public Claims extraerClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(this.clave)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException ex) {
            log.debug("Token JWT invalido o expirado: {}", ex.getMessage());
            return null;
        }
    }
}