package com.bianjp.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties(prefix = "assets")
public class AssetConfig {
  private boolean development = true;
  private String upstream = "http://127.0.0.1:4200/";

  public boolean isDevelopment() {
    return development;
  }

  public void setDevelopment(boolean development) {
    this.development = development;
  }

  public String getUpstream() {
    return upstream;
  }

  public void setUpstream(String upstream) {
    this.upstream = upstream;
  }

  @PostConstruct
  public void formalizeProperties() {
    if (!upstream.endsWith("/")) {
      upstream = upstream + "/";
    }
  }
}
