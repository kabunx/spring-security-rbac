package com.career.work.security.jwt;

import com.career.work.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //
        if (checkJwtRequest(request)) {
            validateJwt(request)
                    .filter(claims -> claims.get("authorities") != null)
                    .ifPresent(claims -> {
                        // 解析jwt中的用户信息，处理后赋值给Security
                        List<?> raws = toList(claims.get("authorities"));
                        List<SimpleGrantedAuthority> authorities = raws.stream()
                                .map(String::valueOf)
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                claims.getSubject(), // 用户名
                                null,      // 用户信息
                                authorities          // 角色权限信息
                        );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
        }
        filterChain.doFilter(request, response);
    }

    private Optional<Claims> validateJwt(HttpServletRequest request) {
        try {
            String jwt = request.getHeader(JwtUtils.header).replace(JwtUtils.prefix, "");
            return Optional.of(
                    Jwts.parserBuilder().setSigningKey(JwtUtils.key).build().parseClaimsJws(jwt).getBody()
            );
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }

    /**
     * @param request HTTP请求
     * @return 是否包含JWT Headers
     */
    private boolean checkJwtRequest(HttpServletRequest request) {
        String authHeader = request.getHeader(JwtUtils.header);
        return authHeader != null && authHeader.startsWith(JwtUtils.prefix);
    }


    private List<?> toList(Object obj) {
        List<?> list = new ArrayList<>();
        if (obj.getClass().isArray()) {
            list = Arrays.asList((Object[]) obj);
        } else if (obj instanceof Collection) {
            list = new ArrayList<>((Collection<?>) obj);
        }
        return list;
    }
}
