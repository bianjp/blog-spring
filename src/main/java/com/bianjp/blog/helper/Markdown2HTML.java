package com.bianjp.blog.helper;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class Markdown2HTML {
  private static MutableDataSet options = new MutableDataSet();
  private static Parser parser;;
  private static HtmlRenderer renderer;

  static {
    parser = Parser.builder(options).build();
    renderer = HtmlRenderer.builder(options).build();
  }

  public static String render(String content) {
    if (content == null || content.isEmpty()) {
      return content;
    }

    Node document = parser.parse(content);
    return renderer.render(document);
  }
}
