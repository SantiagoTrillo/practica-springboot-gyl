package com.gyl.CrudGyl.seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServicio {
    private static final String LLAVE_SECRETA = "spring-security-crud-gyl";
    private final Algorithm algoritmo = Algorithm.HMAC256(LLAVE_SECRETA);

    public String getToken(UserDetails usuario) {
        return JWT.create().withSubject(usuario.getUsername()).withExpiresAt(crearFechaExpiracion()).sign(algoritmo);
    }

    private Instant crearFechaExpiracion() {
        return LocalDateTime.now().plusMinutes(15).toInstant(ZoneOffset.UTC);
    }

    public String getUsernameFromToken(String token) {
        try {
            JWTVerifier verificador = JWT.require(algoritmo).build();
            DecodedJWT tokenDecodificado = verificador.verify(token);
            return tokenDecodificado.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token inválido o expirado");
        }
    }

    public boolean isTokenValid(String token, UserDetails detallesUsuario) {
        String nombreUsuario = getUsernameFromToken(token);
        return (nombreUsuario != null && nombreUsuario.equals(detallesUsuario.getUsername()));
    }
}