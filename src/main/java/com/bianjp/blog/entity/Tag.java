package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Tag extends BaseEntity {
  @NotEmpty
  @Pattern(regexp = "[^\\s]+", message = "Whitespace characters not allowed")
  private String name;

  private Integer postCount;

  @ManyToMany(mappedBy = "tags")
  private Set<Post> posts = new HashSet<>();

  public void increasePostCount() {
    postCount++;
  }

  public void decreasePostCount() {
    postCount--;
  }
}
