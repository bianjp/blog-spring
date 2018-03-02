package com.bianjp.blog.config;

import com.bianjp.blog.config.freemarker.AssetPathHelper;
import com.bianjp.blog.config.freemarker.JavascriptHelper;
import com.bianjp.blog.config.freemarker.StylesheetHelper;
import freemarker.template.TemplateModelException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FreemarkerConfig {

  public FreemarkerConfig(
      BlogProperties blogProperties,
      AssetPathHelper assetPathHelper,
      JavascriptHelper javascriptHelper,
      StylesheetHelper stylesheetHelper,
      freemarker.template.Configuration configuration)
      throws TemplateModelException {
    configuration.setSharedVariable("blog", blogProperties);
    configuration.setSharedVariable("assetPath", assetPathHelper);
    configuration.setSharedVariable("javascript", javascriptHelper);
    configuration.setSharedVariable("stylesheet", stylesheetHelper);
  }
}
