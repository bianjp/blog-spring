package com.bianjp.blog.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest().permitAll();

    http.csrf().disable();

    http.formLogin()
        .loginPage("/login")
        .failureUrl("/login?error")
        .defaultSuccessUrl("/admin")
        .permitAll();

    http.logout()
        .logoutUrl("/logout")
        .deleteCookies("remember-me")
        .logoutSuccessUrl("/")
        .permitAll();

    // TODO: save key in application properties
    http.rememberMe()
        .tokenValiditySeconds(2592000) // 30 days
        .key("0c458a10-6c3a-4041-8757-3d9dc7831292"); // UUID.randomUUID().toString()

    http.headers().httpStrictTransportSecurity().disable();
  }
}
