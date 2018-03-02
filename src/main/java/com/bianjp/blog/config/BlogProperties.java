package com.bianjp.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@ConfigurationProperties(prefix = "blog")
@Validated
public class BlogProperties {
  /** Root url of the blog, without ending slash. eg. https://bianjp.com */
  @NotEmpty
  @Pattern.List({
    @Pattern(regexp = "https?://[\\w.-_]+", message = "Invalid URL"),
    @Pattern(regexp = ".*[^/]$", message = "Should not end with /")
  })
  private String host;

  /** Title of the blog. Used for HTML <title> */
  @NotEmpty private String title;

  /** Disqus configuration */
  @Valid private final Disqus disqus = new Disqus();

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Disqus getDisqus() {
    return disqus;
  }

  /** Disqus configuration */
  public static class Disqus {
    /** Enable comment */
    @NotNull private Boolean enabled;

    /** Shortname used to uniquely identify your website on Disqus */
    @NotEmpty private String shortname;

    public Boolean getEnabled() {
      return enabled;
    }

    public void setEnabled(Boolean enabled) {
      this.enabled = enabled;
    }

    public String getShortname() {
      return shortname;
    }

    public void setShortname(String shortname) {
      this.shortname = shortname;
    }
  }
}
