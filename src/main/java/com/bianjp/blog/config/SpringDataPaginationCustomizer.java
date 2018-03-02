package com.bianjp.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

/** Use 1 based page number in request parameters. */
@Configuration
public class SpringDataPaginationCustomizer
    implements PageableHandlerMethodArgumentResolverCustomizer {

  @Override
  public void customize(PageableHandlerMethodArgumentResolver pageableResolver) {
    pageableResolver.setOneIndexedParameters(true);
  }
}
