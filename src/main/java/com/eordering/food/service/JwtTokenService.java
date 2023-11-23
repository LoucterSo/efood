package com.eordering.food.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenService {
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    @Value("${jwt.lifetime}")
    private long EXPIRATION_TIME;
    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateTokenForAdmin(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000000000000000000l))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getNameFromJwt(String jwt) {

        return getClaim(jwt, Claims::getSubject);
    }

    private Claims getAllClaims(String jwt) {
        return Jwts
                .parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public <T> T getClaim (String jwt, Function<Claims, T> claimsFunction) {
        final Claims claims = getAllClaims(jwt);
        return claimsFunction.apply(claims);
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = getNameFromJwt(jwt);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwt);
    }

    public boolean isTokenExpired(String jwt) {
        return getExpiration(jwt).before(new Date());
    }

    private Date getExpiration(String jwt) {
        return getClaim(jwt, Claims::getExpiration);
    }

}
