package com.bianjp.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FreemarkerConfig extends FreeMarkerAutoConfiguration.FreeMarkerWebConfiguration {

  @Autowired private ApplicationContext context;

  @Override
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = super.freeMarkerConfigurer();
    Map<String, Object> sharedVariables = new HashMap<>();
    sharedVariables.put("assetPath", context.getBean(AssetPathHelper.class));
    configurer.setFreemarkerVariables(sharedVariables);

    return configurer;
  }
}
