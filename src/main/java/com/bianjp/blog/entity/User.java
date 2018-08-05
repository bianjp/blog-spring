package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@ToString
public class User extends BaseEntity {
  private String username;
  @JsonIgnore private String password;
}
