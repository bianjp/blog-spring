package com.bianjp.blog.config;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Used by {@link AbstractErrorController#resolveErrorView(HttpServletRequest, HttpServletResponse,
 * HttpStatus, Map)}
 */
@Component
public class CustomErrorViewResolver implements ErrorViewResolver {
  @Override
  public ModelAndView resolveErrorView(
      HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
    ModelAndView modelAndView =
        new ModelAndView("frontend/errors/" + getTemplateName(status), model);
    return modelAndView;
  }

  private String getTemplateName(HttpStatus status) {
    if (status == HttpStatus.NOT_FOUND) {
      return "404";
    }
    return "500";
  }
}
