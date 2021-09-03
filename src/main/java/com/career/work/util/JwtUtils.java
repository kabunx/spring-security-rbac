package com.career.work.util;

import com.career.work.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    public static final String claimName = "user_id";
    public static final String jti = "kabunx";
    public static final String aud = "frontend";
    public static final String iss = "kabunx";

    // 用于签名 Access Token
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // HTTP 报头的认证字段的 key
    public static final String header = "Authorization";

    // HTTP 报头的认证字段的值的前缀
    public static final String tokenPrefix = "Bearer ";

    // Access Token 过期时间
    @Min(5000L)
    public static final long accessTokenExpireTime = 60 * 1000L;

    // Refresh Token 过期时间
    @Min(3600000L)
    public static final long refreshTokenExpireTime = 30 * 24 * 3600 * 1000L;

    public String generateAccessToken(User user) {
        return generateAccessToken(user, accessTokenExpireTime);
    }

    public String generateAccessToken(User user, long timeToExpire) {
        return generateAccessToken(user, timeToExpire, key);
    }

    public String generateAccessToken(User user, long timeToExpire, Key signKey) {
        long now = System.currentTimeMillis();

        return Jwts
                .builder()
                .setId(jti)
                .setAudience(aud)
                .setIssuer(iss)
                .setSubject(user.getUsername())
                .claim(claimName, user.getId())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + timeToExpire))
                .signWith(signKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateAccessToken(String jwtToken) {
        return validateToken(jwtToken, key);
    }


    public boolean validateToken(String jwtToken, Key signKey) {
        Optional<Claims> optional = parseClaims(jwtToken, signKey);
        return optional.isPresent();
    }

    public Optional<Claims> parseClaims(String accessToken) {
        return parseClaims(accessToken, key);
    }

    public Optional<Claims> parseClaims(String accessToken, Key signKey) {
        try {
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(signKey)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
            return Optional.of(claims);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean validateWithoutExpiration(String jwtToken) {
        try {
            Jwts.parserBuilder()
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
        if (userDetails.getAuthorities() == null) {
            return null;
        }
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
