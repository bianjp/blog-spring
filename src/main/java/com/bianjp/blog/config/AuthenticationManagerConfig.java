package com.bianjp.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthenticationManagerConfig {
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * {@link UserDetailsServiceAutoConfiguration} will not be enabled when custom UserDetailService
   * bean is present. Incorrect IDE warning of multiple beans for UserDetailService type can be
   * safely suppressed.
   */
  @Autowired
  public void configUserDetailService(
      AuthenticationManagerBuilder auth,
      @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
          UserDetailsService userDetailsService)
      throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
