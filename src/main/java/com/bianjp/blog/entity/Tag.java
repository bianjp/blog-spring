package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class Tag extends BaseEntity {
  @NotEmpty
  @Pattern(regexp = "[^\\s]+", message = "Whitespace characters not allowed")
  private String name;

  private Integer postCount;

  @ManyToMany(mappedBy = "tags")
  private Set<Post> posts = new HashSet<>();

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getPostCount() {
    return postCount;
  }

  public void setPostCount(Integer postCount) {
    this.postCount = postCount;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
  }

  public void increasePostCount() {
    postCount++;
  }

  public void decreasePostCount() {
    postCount--;
  }

  @Override
  public String toString() {
    return "Tag{"
        + "name='"
        + name
        + '\''
        + ", postCount="
        + postCount
        + ", posts="
        + posts
        + ", id="
        + id
        + ", updatedAt="
        + updatedAt
        + ", createdAt="
        + createdAt
        + '}';
  }
}
