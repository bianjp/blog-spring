package com.bianjp.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "assets")
public class AssetConfig {
  private final Broccoli broccoli = new Broccoli();

  public Broccoli getBroccoli() {
    return broccoli;
  }

  public boolean isDevelopment() {
    return broccoli.enabled;
  }

  public String getUpstream() {
    return broccoli.getUrl();
  }

  public static class Broccoli {
    private boolean enabled = false;
    private String host = "localhost";
    private int port = 4200;

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getHost() {
      return host;
    }

    public void setHost(String host) {
      this.host = host;
    }

    public int getPort() {
      return port;
    }

    public void setPort(int port) {
      this.port = port;
    }

    public String getUrl() {
      return "http://" + host + ":" + port + "/";
    }
  }
}
