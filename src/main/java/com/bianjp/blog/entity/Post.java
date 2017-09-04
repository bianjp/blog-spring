package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import com.bianjp.blog.entity_helper.LocalDateConverter;
import com.bianjp.blog.entity_helper.PostStatusConverter;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Entity;

@Entity
public class Post extends BaseEntity {
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

    public static Status findByCode(int code) {
      for (Status status : Status.values()) {
        if (status.code == code) {
          return status;
        }
      }

      return null;
    }
  }

  private String title;
  @Convert(converter = LocalDateConverter.class)
  private LocalDate publishDate;
  private String slug;
  private String content;
  private String contentHtml;

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
  }

  public String getContentHtml() {
    return contentHtml;
  }

  public void setContentHtml(String contentHtml) {
    this.contentHtml = contentHtml;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getPrettyUrl() {
    if (publishDate == null) {
      return Integer.toString(id);
    }

    return publishDate.toString("yyyy/MM/dd") + "/" + slug;
  }
}
