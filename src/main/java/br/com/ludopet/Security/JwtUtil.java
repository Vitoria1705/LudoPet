package br.com.ludopet.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET =
            "segredo123456789012345678901234567890";

    private static final Key KEY =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes(StandardCharsets.UTF_8)
            );

    public static String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .expiration(
                        new Date(
                                System.currentTimeMillis() + 86400000
                        )
                )
                .signWith(KEY, SignatureAlgorithm.HS512)
                .compact();
    }
}