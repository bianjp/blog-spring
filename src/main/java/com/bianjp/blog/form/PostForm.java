package com.bianjp.blog.form;

import com.bianjp.blog.entity.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostForm {
  @NotNull
  @Size(min = 1, max = 100)
  private String title;

  @NotNull
  @Size(min = 1, max = 100)
  @Pattern(regexp = "[\\w\\-_]+")
  private String slug;

  @NotNull
  @Size(min = 1)
  private String content;

  @NotNull private List<String> tags = new ArrayList<>();

  @NotNull private Post.Status status;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Post.Status getStatus() {
    return status;
  }

  public void setStatus(Post.Status status) {
    this.status = status;
  }

  public String getTagsText() {
    return String.join(",", tags);
  }

  public void setTagsText(String tagsText) {
    tags.clear();
    if (tagsText != null && !tagsText.isEmpty()) {
      Collections.addAll(tags, tagsText.split(","));
    }
  }

  public String getStatusText() {
    return status.getText();
  }

  public void setStatusText(String statusText) {
    this.status = Post.Status.findByText(statusText);
  }

  @Override
  public String toString() {
    return "PostForm{"
        + "title='"
        + title
        + '\''
        + ", slug='"
        + slug
        + '\''
        + ", content='"
        + content
        + '\''
        + ", tags="
        + tags
        + ", status="
        + status
        + '}';
  }
}
