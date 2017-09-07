package com.bianjp.blog.helper;

import com.vladsch.flexmark.ast.Document;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;

public class Markdown2HTML {
  private static final int MAX_EXCERPT_NODES = 10;
  private static MutableDataSet options = new MutableDataSet();
  private static Parser parser;
  private static HtmlRenderer renderer;

  static {
    parser = Parser.builder(options).build();
    renderer = HtmlRenderer.builder(options).build();
  }

  public static String render(String content) {
    if (content == null || content.isEmpty()) {
      return content;
    }

    Document document = parser.parse(content);
    return renderer.render(document);
  }

  // Take MAX_EXCERPT_NODES top level nodes as excerpt
  public static String renderExcerpt(String content) {
    if (content == null || content.isEmpty()) {
      return content;
    }

    Document document = parser.parse(content);
    int i = 0;
    for (Node node : document.getChildren()) {
      if (i++ >= MAX_EXCERPT_NODES) {
        node.unlink();
      }
    }

    return renderer.render(document);
  }
}
