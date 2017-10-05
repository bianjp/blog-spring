package com.bianjp.blog.config.freemarker;

import com.bianjp.blog.config.AssetConfig;
import org.springframework.stereotype.Component;

@Component
public class JavascriptHelper extends AbstractCdnPathHelper {
  public JavascriptHelper(AssetConfig assetConfig) {
    super(assetConfig);
  }

  @Override
  protected String assetType() {
    return "javascript";
  }

  @Override
  protected String fileSuffix() {
    return ".js";
  }

  @Override
  protected String buildHTMLTag(String path) {
    return String.format("<script src=\"%s\"></script>", path);
  }
}
