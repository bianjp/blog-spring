package com.bianjp.blog.entity_helper;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {
  private static final long serialVersionUID = 100L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;

  @Convert(converter = DateTimeConverter.class)
  protected DateTime updatedAt;

  @Convert(converter = DateTimeConverter.class)
  protected DateTime createdAt;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public DateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(DateTime createdAt) {
    this.createdAt = createdAt;
  }

  public DateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(DateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public boolean isNewRecord() {
    return id == null || id == 0;
  }

  @PrePersist
  public void prePersist() {
    if (isNewRecord()) {
      createdAt = new DateTime();
    }
    updatedAt = new DateTime();
  }
}
