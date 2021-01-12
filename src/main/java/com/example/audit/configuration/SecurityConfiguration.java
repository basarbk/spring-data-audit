package com.example.audit.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.httpBasic();

    http
      .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/api/1.0/articles").authenticated()
        .antMatchers(HttpMethod.PUT, "/api/1.0/articles/{id}").authenticated()
      .and()
        .authorizeRequests().anyRequest().permitAll();

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    auth.inMemoryAuthentication()
      .withUser("user1").password(passwordEncoder.encode("P4ssword")).roles("user")
      .and()
      .withUser("user2").password(passwordEncoder.encode("P4ssword")).roles("user")
      .and()
      .passwordEncoder(passwordEncoder);
  }
  
}
