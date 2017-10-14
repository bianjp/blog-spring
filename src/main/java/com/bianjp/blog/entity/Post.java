package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import com.bianjp.blog.entity_helper.LocalDateConverter;
import com.bianjp.blog.entity_helper.PostStatusConverter;
import com.bianjp.blog.helper.Markdown2HTML;
import org.joda.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Post extends BaseEntity {
  @NotNull
  @Size(min = 1, max = 100)
  private String title;

  @Convert(converter = LocalDateConverter.class)
  private LocalDate publishDate;

  @NotNull
  @Size(min = 1, max = 100)
  @Pattern(regexp = "[\\w\\-_]+")
  private String slug;

  @NotNull
  @Size(min = 1)
  private String content;

  private String contentHtml;
  private String excerpt;

  @Convert(converter = PostStatusConverter.class)
  private Status status = Status.DRAFT;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(LocalDate publishDate) {
    this.publishDate = publishDate;
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
    this.contentHtml = Markdown2HTML.render(content);
    this.excerpt = Markdown2HTML.renderExcerpt(content);
  }

  public String getContentHtml() {
    return contentHtml;
  }

  public void setContentHtml(String contentHtml) {
    this.contentHtml = contentHtml;
  }

  public String getExcerpt() {
    return excerpt;
  }

  public void setExcerpt(String excerpt) {
    this.excerpt = excerpt;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Transient
  public boolean isDraft() {
    return status == Status.DRAFT;
  }

  @Transient
  public boolean isPublished() {
    return status == Status.PUBLISHED;
  }

  public void initPublishDate() {
    if (publishDate == null) {
      publishDate = new LocalDate();
    }
  }

  @Transient
  public String getPrettyUrl() {
    if (publishDate == null) {
      return "";
    }

    return publishDate.toString("yyyy/MM/dd") + "/" + slug;
  }

  @Override
  public String toString() {
    return "Post{"
        + "title='"
        + title
        + '\''
        + ", publishDate="
        + publishDate
        + ", slug='"
        + slug
        + '\''
        + ", content='"
        + content
        + '\''
        + ", contentHtml='"
        + contentHtml
        + '\''
        + ", status="
        + status
        + ", id="
        + id
        + ", updatedAt="
        + updatedAt
        + ", createdAt="
        + createdAt
        + '}';
  }

  public enum Status {
    DELETED(-1, "deleted"),
    DRAFT(0, "draft"),
    PUBLISHED(1, "published"),
    UNPUBLISHED(2, "unpublished");

    private int code;
    private String text;

    Status(int code, String text) {
      this.code = code;
      this.text = text;
    }

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getText() {
      return text;
    }

    public void setText(String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }

    public static Status findByCode(int code) {
      for (Status status : Status.values()) {
        if (status.code == code) {
          return status;
        }
      }

      return null;
    }

    public static Status findByText(String text) {
      for (Status status : Status.values()) {
        if (status.text.equals(text)) {
          return status;
        }
      }

      return null;
    }
  }
}
