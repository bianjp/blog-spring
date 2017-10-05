package com.bianjp.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "assets")
public class AssetConfig {
  private final CDN cdn = new CDN();
  private final Broccoli broccoli = new Broccoli();
  private final Map<String, List<String>> cdnPath = new HashMap<>();
  private final Map<String, List<String>> npmPath = new HashMap<>();

  public CDN getCdn() {
    return cdn;
  }

  public Broccoli getBroccoli() {
    return broccoli;
  }

  public boolean isDevelopment() {
    return broccoli.enabled;
  }

  public String getUpstream() {
    return broccoli.getUrl();
  }

  public Map<String, List<String>> getCdnPath() {
    return cdnPath;
  }

  public Map<String, List<String>> getNpmPath() {
    return npmPath;
  }

  @PostConstruct
  public void validateProvider() {
    if (cdn.getProvider() != null) {
      if (!cdn.getAvailableProviders().containsKey(cdn.getProvider())) {
        throw new RuntimeException("CDN provider " + cdn.getProvider() + " not found");
      }
    }
  }

  @Override
  public String toString() {
    return "AssetConfig{"
        + "cdn="
        + cdn
        + ", broccoli="
        + broccoli
        + ", cdnPath="
        + cdnPath
        + ", npmPath="
        + npmPath
        + '}';
  }

  public static class CDN {
    private boolean enabled = false;
    private String provider;
    private final Map<String, String> availableProviders = new HashMap<>();

    public boolean isEnabled() {
      return enabled;
    }

    public void setEnabled(boolean enabled) {
      this.enabled = enabled;
    }

    public String getProvider() {
      return provider;
    }

    public void setProvider(String provider) {
      this.provider = provider;
    }

    public Map<String, String> getAvailableProviders() {
      return availableProviders;
    }

    @Override
    public String toString() {
      return "CDN{"
          + "enabled="
          + enabled
          + ", provider='"
          + provider
          + '\''
          + ", availableProviders="
          + availableProviders
          + '}';
    }
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

    @Override
    public String toString() {
      return "Broccoli{" + "enabled=" + enabled + ", host='" + host + '\'' + ", port=" + port + '}';
    }
  }
}
