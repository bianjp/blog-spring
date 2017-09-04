package com.bianjp.blog.entity_helper;

import org.joda.time.DateTime;

import javax.persistence.Convert;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {
  @Id protected int id;

  @Convert(converter = DateTimeConverter.class)
  protected DateTime updatedAt;

  @Convert(converter = DateTimeConverter.class)
  protected DateTime createdAt;

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  @PrePersist
  public void prePersist() {
    if (id == 0) {
      createdAt = new DateTime();
    }
    updatedAt = new DateTime();
  }
}
