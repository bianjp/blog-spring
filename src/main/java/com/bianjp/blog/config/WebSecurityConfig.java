package com.bianjp.blog.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER + 1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
          .antMatchers("/admin/**").hasAnyRole("ADMIN")
          .anyRequest().permitAll()
          .and()
        .csrf().disable()
        .formLogin()
          .loginPage("/login")
          .failureUrl("/login?error")
          .defaultSuccessUrl("/admin")
          .permitAll()
          .and()
        .logout()
          .logoutUrl("/logout")
          .deleteCookies("remember-me")
          .logoutSuccessUrl("/")
          .permitAll()
          .and()
        .rememberMe()
          .and()
        .headers()
          .defaultsDisabled()
          .frameOptions().sameOrigin()
          .contentTypeOptions().disable();
  }
}
