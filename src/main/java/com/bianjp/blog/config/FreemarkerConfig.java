package com.bianjp.blog.config;

import com.bianjp.blog.config.freemarker.AssetPathHelper;
import com.bianjp.blog.config.freemarker.JavascriptHelper;
import com.bianjp.blog.config.freemarker.StylesheetHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FreemarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

  private final AssetPathHelper assetPathHelper;
  private final BlogProperties blogProperties;
  private final JavascriptHelper javascriptHelper;
  private final StylesheetHelper stylesheetHelper;

  @Autowired
  public FreemarkerConfig(AssetPathHelper assetPathHelper, BlogProperties blogProperties, JavascriptHelper javascriptHelper, StylesheetHelper stylesheetHelper) {
    this.assetPathHelper = assetPathHelper;
    this.blogProperties = blogProperties;
    this.javascriptHelper = javascriptHelper;
    this.stylesheetHelper = stylesheetHelper;
  }

  @Override
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
    Map<String, Object> sharedVariables = new HashMap<>();
    sharedVariables.put("assetPath", assetPathHelper);
    sharedVariables.put("blog", blogProperties);
    sharedVariables.put("javascript", javascriptHelper);
    sharedVariables.put("stylesheet", stylesheetHelper);
    configurer.setFreemarkerVariables(sharedVariables);

    return configurer;
  }
}
