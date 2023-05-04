package voll.api.domain.infra.security;

import java.time.Instant;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;


import voll.api.domain.users.Usuario;


import java.time.LocalDateTime;


@Service
public class TokenService {

    //we get this from application.yml
    @Value("${api.security.secret}")
    public String apiSecret;
    

    public String generarToken(Usuario usuario) throws Exception{
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                .withIssuer("voll med")
                .withSubject(usuario.getLogin())
                .withClaim("id", usuario.getId())
                .withExpiresAt(generarFechaExpiracion())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new Exception(exception);
        }
    }


    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); // validando firma
            verifier = JWT.require(algorithm)
                    .withIssuer("voll med")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }


    private Instant generarFechaExpiracion() {
        ZoneId zonaHoraria = ZoneOffset.ofHoursMinutes(-5, 0);
        ZonedDateTime zdt = ZonedDateTime.now(zonaHoraria).plusHours(2);
        return zdt.toInstant();

    }
}
