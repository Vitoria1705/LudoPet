package br.com.ludopet.Security;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "segredo";

    public static String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}