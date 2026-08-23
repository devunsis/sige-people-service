package mx.edu.unsis.sige.shared.infrastructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import mx.edu.unsis.sige.shared.infrastructure.security.model.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    private final SecretKey clave;
    private final long expiracionMinutos;
    private final String issuer;

    public JwtProvider(
            @Value("${app.security.jwt.secret}") String secret,
            @Value("${app.security.jwt.expiration-minutes:60}") long expiracionMinutos,
            @Value("${app.security.jwt.issuer:sige-auth-service}") String issuer) {

        if (secret.getBytes(StandardCharsets.UTF_8).length < 32) {
            throw new IllegalArgumentException("La clave secreta JWT debe tener al menos 32 caracteres (256 bits).");
        }

        this.clave = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiracionMinutos = expiracionMinutos;
        this.issuer = issuer;
    }

    public String generarToken(UserPrincipal userPrincipal) {
        Instant ahora = Instant.now();

        return Jwts.builder()
                .subject(userPrincipal.getId().toString())
                .issuer(this.issuer)
                .claim("username", userPrincipal.getUsername())
                .claim("email", userPrincipal.getEmail())
                .claim("roles", userPrincipal.getRoles())
                .claim("permisos", userPrincipal.getPermisos())
                .issuedAt(Date.from(ahora))
                .expiration(Date.from(ahora.plus(expiracionMinutos, ChronoUnit.MINUTES)))
                .signWith(clave)
                .compact();
    }

    public Claims extraerClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(this.clave)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException ex) {
            log.warn("Token JWT expirado: {}", ex.getMessage());
        } catch (SignatureException ex) {
            log.error("Firma JWT inválida / manipulada: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Token JWT malformado: {}", ex.getMessage());
        } catch (JwtException | IllegalArgumentException ex) {
            log.error("Error al procesar el token JWT: {}", ex.getMessage());
        }
        return null;
    }

    public boolean isTokenValid(String token) {
        return extraerClaims(token) != null;
    }

    public UUID extraerUserId(String token) {
        Claims claims = extraerClaims(token);
        return (claims != null) ? UUID.fromString(claims.getSubject()) : null;
    }
}