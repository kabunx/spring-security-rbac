package com.career.work.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    // 用于签名 Access Token
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // 用于签名 Refresh Token
    public static final Key refreshKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // HTTP 报头的认证字段的 key
    public static final String header = "Authorization";

    // HTTP 报头的认证字段的值的前缀
    public static final String prefix = "Bearer ";

    // Access Token 过期时间
    @Min(5000L)
    public final long accessTokenExpireTime = 60 * 1000L;

    // Refresh Token 过期时间
    @Min(3600000L)
    public final long refreshTokenExpireTime = 30 * 24 * 3600 * 1000L;

    public String generateJwtToken(UserDetails userDetails) {
        return generateJwtToken(userDetails, accessTokenExpireTime);
    }

    public String generateJwtToken(UserDetails userDetails, long timeToExpire) {
        return generateJwtToken(userDetails, timeToExpire, key);
    }

    public String generateJwtToken(UserDetails userDetails, long timeToExpire, Key signKey) {
        long now = System.currentTimeMillis();

        return Jwts
                .builder()
                .setId("kabunx")
                .setSubject(userDetails.getUsername())
                .claim("user", userDetails)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + timeToExpire))
                .signWith(signKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateJwtToken(userDetails, refreshTokenExpireTime, refreshKey);
    }

    public boolean validateAccessToken(String jwtToken) {
        return validateToken(jwtToken, key);
    }

    public boolean validateRefreshToken(String jwtToken) {
        return validateToken(jwtToken, refreshKey);
    }

    public boolean validateToken(String jwtToken, Key signKey) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(signKey)
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public String buildAccessTokenWithRefreshToken(String jwtToken) {
        return parseClaims(jwtToken, refreshKey)
                .map(claims -> Jwts
                        .builder()
                        .setClaims(claims)
                        .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireTime))
                        .signWith(key, SignatureAlgorithm.HS512).compact()
                )
                .orElseThrow(() -> new JwtException("jwt"));
    }

    public Optional<Claims> parseClaims(String jwtToken, Key signKey) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(signKey)
                    .build()
                    .parseClaimsJws(jwtToken)
                    .getBody();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean validateWithoutExpiration(String jwtToken) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e) {
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    // 字符串集合
    private List<String> claimUserDetails(UserDetails userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (authorities == null) {
            return null;
        }
        return authorities
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
