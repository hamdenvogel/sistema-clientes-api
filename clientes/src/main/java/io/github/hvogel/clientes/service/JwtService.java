package io.github.hvogel.clientes.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.github.hvogel.clientes.model.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    public String gerarToken(Usuario usuario) {
        long expString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();

        return Jwts
                .builder()
                .setSubject(usuario.getUsername())
                .setExpiration(Date.from(instant))
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(chaveAssinatura)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(data);
        } catch (ExpiredJwtException | io.jsonwebtoken.MalformedJwtException | io.jsonwebtoken.SignatureException
                | IllegalArgumentException e) {
            logger.debug("Invalid or expired token: {}", e.getMessage());
            return false;
        }
    }

    public LocalDateTime obterTempoExpiracao(String token) {
        try {
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            return dataExpiracao.toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (ExpiredJwtException | io.jsonwebtoken.MalformedJwtException | io.jsonwebtoken.SignatureException
                | IllegalArgumentException e) {
            logger.debug("Cannot get expiration time from invalid token: {}", e.getMessage());
            return null;
        }
    }

    public Integer obterTempoExpiracaoEmSegundos() {
        try {
            return Integer.valueOf(expiracao) * 60;
        } catch (NumberFormatException ex) {
            logger.error("Error parsing expiration time", ex);
        }
        return null;
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject();
    }
}
