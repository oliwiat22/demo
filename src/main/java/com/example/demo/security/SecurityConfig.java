package com.example.demo.security;

import com.example.demo.security.jwt.JwtConfig;
import com.example.demo.security.jwt.JwtTokenVerifier;
import com.example.demo.security.jwt.JwtUsernameAndPasswordAuthFilter;
import com.example.demo.user.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.servlet.Filter;

import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final PasswordEncoder passwordEncoder;
  private final UserDetailsServiceImpl userDetailsService;
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Autowired
  public SecurityConfig(PasswordEncoder passwordEncoder,
                        UserDetailsServiceImpl userDetailsService,
                        SecretKey secretKey,
                        JwtConfig jwtConfig) {
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
    this.secretKey = secretKey;
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors()
        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(), jwtConfig, secretKey))
        .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthFilter.class)
        .authorizeRequests()
        .anyRequest()
        .authenticated();
  }

  private DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);

    return authProvider;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(authenticationProvider());
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/register")
        .antMatchers("/user/create")
        .antMatchers(GET, "/item/image/**")
        .antMatchers("/swagger.json")
        .antMatchers("/v2/api-docs/**")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/configuration/ui")
        .antMatchers("/configuration/security")
        .antMatchers("/webjars/**");
  }

}
