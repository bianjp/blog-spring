package com.bianjp.blog.form;

import com.bianjp.blog.entity.Post;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

  @NotNull private String statusText;

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

  public String getStatusText() {
    return statusText;
  }

  public void setStatusText(String statusText) {
    this.statusText = statusText;
    this.status = Post.Status.findByText(statusText);
  }

  public Post.Status getStatus() {
    return status;
  }

  public void setStatus(Post.Status status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "PostForm{" +
      "title='" + title + '\'' +
      ", slug='" + slug + '\'' +
      ", content='" + content + '\'' +
      ", statusText='" + statusText + '\'' +
      ", status=" + status +
      '}';
  }
}
