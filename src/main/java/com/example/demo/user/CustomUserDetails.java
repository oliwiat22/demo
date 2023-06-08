package com.example.demo.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomUserDetails implements UserDetails {

  Long userId;
  String username;
  String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<>();
  }

  public Long getUserId() {
    return userId;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
