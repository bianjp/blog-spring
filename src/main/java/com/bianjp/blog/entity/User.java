package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User extends BaseEntity {
  private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = passwordEncoder.encode(password);
  }

  public boolean validatePassword(String rawPassword) {
    return passwordEncoder.matches(rawPassword, password);
  }
}
