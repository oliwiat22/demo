package com.example.demo.security.jwt;

import com.google.common.net.HttpHeaders;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "application.jwt")
public class JwtConfig {
  private String secretKey;
  private String tokenPrefix;
  private int tokenExpirationAfterMinutes;

  public JwtConfig() {
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  public String getTokenPrefix() {
    return this.tokenPrefix;
  }

  public void setTokenPrefix(String tokenPrefix) {
    this.tokenPrefix = tokenPrefix;
  }

  public int getTokenExpirationAfterMinutes() {
    return this.tokenExpirationAfterMinutes;
  }

  public void setTokenExpirationAfterMinutes(int tokenExpirationAfterMinutes) {
    this.tokenExpirationAfterMinutes = tokenExpirationAfterMinutes;
  }

  public String getAuthorizationHeader() {
    return HttpHeaders.AUTHORIZATION;
  }

}
