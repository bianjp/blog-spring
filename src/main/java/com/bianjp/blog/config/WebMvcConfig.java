package com.bianjp.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  private final AssetConfig assetConfig;

  @Autowired
  public WebMvcConfig(AssetConfig assetConfig) {
    this.assetConfig = assetConfig;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (assetConfig.isDevelopment()) {
      registry
          .addResourceHandler("/assets/**")
          .setCachePeriod(0)
          .resourceChain(false)
          .addResolver(new AssetUpstreamResolver(assetConfig));
    } else {
      registry
          .addResourceHandler("/assets/**")
          .addResourceLocations("classpath:assets/")
          .setCachePeriod(3600 * 24 * 365);
    }
  }
}
