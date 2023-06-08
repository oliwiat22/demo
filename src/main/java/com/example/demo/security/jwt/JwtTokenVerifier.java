package com.example.demo.security.jwt;

import com.example.demo.user.CustomUserDetails;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class JwtTokenVerifier extends OncePerRequestFilter {

  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  public JwtTokenVerifier(SecretKey secretKey, JwtConfig jwtConfig) {
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

    if(Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = authorizationHeader.replaceFirst(jwtConfig.getTokenPrefix(), "");

    try {
      Jws<Claims> claimsJws = Jwts.parser()
          .setSigningKey(secretKey)
          .parseClaimsJws(token);

      Claims body = claimsJws.getBody();
      final String username = body.getSubject();
      final long userId = ((Number) body.get("userId")).longValue();
      final UserDetails userDetails = CustomUserDetails.builder()
          .userId(userId)
          .username(username)
          .build();
      List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
      Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
          .map(auth -> new SimpleGrantedAuthority(auth.get("authority")))
          .collect(toSet());

      Authentication authentication = new UsernamePasswordAuthenticationToken(
          userDetails, null, simpleGrantedAuthorities
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
    }

    filterChain.doFilter(request, response);
  }
  
}
