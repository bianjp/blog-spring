package com.bianjp.blog.helper;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaginationHelper {
  private static Pattern pageParamPattern = Pattern.compile("(^|&)page($|=[^&]*)");

  public static void setPageLink(Model model, HttpServletRequest request) {
    String queryString = request.getQueryString();
    if (queryString == null || queryString.isEmpty()) {
      queryString = "page={page}";
    } else {
      Matcher m = pageParamPattern.matcher(queryString);
      if (m.find()) {
        queryString = m.replaceFirst(m.group(1) + "page={page}");
      } else {
        queryString += "&page={page}";
      }
    }
    String pageLink = request.getRequestURI() + "?" + queryString;

    model.addAttribute("pageLink", pageLink);
  }
}
