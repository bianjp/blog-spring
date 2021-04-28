package com.bianjp.blog.entity;

import com.bianjp.blog.entity_helper.BaseEntity;
import com.bianjp.blog.entity_helper.LocalDateConverter;
import com.bianjp.blog.entity_helper.PostStatusConverter;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Options;
import org.asciidoctor.ast.Document;
import org.joda.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Post extends BaseEntity {
  private static final Asciidoctor asciidoctor = Asciidoctor.Factory.create();

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

  /**
   * Use Set instead of List to avoid efficiency issues (delete all and recreate when updating)
   * https://stackoverflow.com/a/46887535/3128576
   */
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "post_tag",
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags = new HashSet<>();

  public void setContent(String content) {
    this.content = content;
    Document document = asciidoctor.load(content, Options.builder().build());
    this.contentHtml = document.convert();
    this.excerpt = document.getBlocks().get(0).convert();
  }

  public void addTag(Tag tag) {
    tags.add(tag);
    tag.increasePostCount();
    tag.getPosts().add(this);
  }

  public void removeTag(Tag tag) {
    tags.remove(tag);
    tag.decreasePostCount();
    tag.getPosts().remove(this);
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

  @Getter
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
