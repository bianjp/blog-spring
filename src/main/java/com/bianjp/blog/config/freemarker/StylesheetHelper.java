package com.bianjp.blog.config.freemarker;

import com.bianjp.blog.config.AssetConfig;
import org.springframework.stereotype.Component;

@Component
public class StylesheetHelper extends AbstractCdnPathHelper {
  public StylesheetHelper(AssetConfig assetConfig) {
    super(assetConfig);
  }

  @Override
  protected String assetType() {
    return "stylesheet";
  }

  @Override
  protected String fileSuffix() {
    return ".css";
  }

  @Override
  protected String buildHTMLTag(String path) {
    return String.format("<link rel=\"stylesheet\" href=\"%s\">", path);
  }
}
