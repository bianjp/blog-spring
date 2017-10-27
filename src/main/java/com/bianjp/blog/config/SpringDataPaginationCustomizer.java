package com.bianjp.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.SpringDataWebConfiguration;

/**
 * Use 1 based page number in request parameters . Use
 * PageableHandlerMethodArgumentResolverCustomizer.customize instead if spring-data-commons >= 1.6
 */
@Configuration
public class SpringDataPaginationCustomizer extends SpringDataWebConfiguration {

  @Override
  public PageableHandlerMethodArgumentResolver pageableResolver() {
    PageableHandlerMethodArgumentResolver resolver =
        new PageableHandlerMethodArgumentResolver(sortResolver());
    resolver.setOneIndexedParameters(true);
    return resolver;
  }
}
