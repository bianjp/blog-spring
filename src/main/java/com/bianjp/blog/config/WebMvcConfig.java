package com.bianjp.blog.config;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private final AssetConfig assetConfig;
  private final UploadProperties uploadProperties;

  @Autowired
  public WebMvcConfig(AssetConfig assetConfig, UploadProperties uploadProperties) {
    this.assetConfig = assetConfig;
    this.uploadProperties = uploadProperties;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/uploads/**")
        .addResourceLocations(uploadProperties.getStorageDirUri())
        .setCachePeriod(3600 * 24);

    if (!assetConfig.getCdn().isEnabled()) {
      registry
          .addResourceHandler("/npm-assets/**")
          .addResourceLocations(new File("node_modules").toURI().toString())
          .setCachePeriod(0);
    }

    if (assetConfig.isDevelopment()) {
      registry
          .addResourceHandler("/assets/**")
          .addResourceLocations(assetConfig.getUpstream())
          .setCachePeriod(0);
    } else {
      registry
          .addResourceHandler("/assets/**")
          .addResourceLocations("classpath:assets/")
          .setCachePeriod(3600 * 24 * 365);
    }
  }
}
