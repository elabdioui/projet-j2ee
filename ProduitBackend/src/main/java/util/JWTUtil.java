package util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JWTUtil {

    // Clé secrète - à changer en production
    public static final String SECRET = "mySecret1234";
    public static final String AUTH_HEADER = "Authorization";
    public static final String AUTH_HEADER_PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 10 * 60 * 1000; // 10 minutes
    public static final long EXPIRE_REFRESH_TOKEN = 30 * 60 * 1000; // 30 minutes

    public String generateAccessToken(String username, List<String> roles) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withSubject(username)
                .withArrayClaim("roles", roles.toArray(new String[0]))
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_ACCESS_TOKEN))
                .sign(algorithm);
    }

    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_REFRESH_TOKEN))
                .sign(algorithm);
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public List<String> extractRoles(String token) {
        return extractClaims(token).getClaim("roles").asList(String.class);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiresAt();
    }

    public DecodedJWT extractClaims(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public Boolean isTokenExpired(String token) {
        try {
            return extractExpiration(token).before(new Date());
        } catch (JWTVerificationException e) {
            return true;
        }
    }

    public Boolean validateToken(String token, String username) {
        try {
            final String extractedUsername = extractUsername(token);
            return (extractedUsername.equals(username) && !isTokenExpired(token));
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}