package com.bianjp.blog.config.freemarker;

import com.bianjp.blog.config.AssetConfig;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCdnPathHelper implements TemplateMethodModelEx {
  private final AssetConfig assetConfig;
  private final AssetConfig.CDN cdn;
  private final String pathPrefix;

  public AbstractCdnPathHelper(AssetConfig assetConfig) {
    this.assetConfig = assetConfig;
    cdn = assetConfig.getCdn();
    if (cdn.isEnabled()) {
      pathPrefix = cdn.getAvailableProviders().get(cdn.getProvider());
    } else {
      pathPrefix = "/npm-assets/";
    }
  }

  @Override
  public Object exec(List arguments) throws TemplateModelException {
    if (arguments.size() != 1) {
      throw new TemplateModelException(
          "Wrong number of parameters, need 1, passed " + arguments.size());
    } else if (!(arguments.get(0) instanceof SimpleScalar)) {
      throw new TemplateModelException("Wrong parameter type: Only string permitted!");
    }

    String name = ((SimpleScalar) arguments.get(0)).getAsString();
    List<String> paths;

    if (cdn.isEnabled()) {
      paths = assetConfig.getCdnPath().get(name);
    } else {
      paths = assetConfig.getNpmPath().get(name);
    }

    if (paths != null && !paths.isEmpty()) {
      paths =
          paths.stream().filter(path -> path.endsWith(fileSuffix())).collect(Collectors.toList());
    }

    if (paths == null || paths.isEmpty()) {
      throw new TemplateModelException("No " + assetType() + " assets found for " + name);
    }

    StringBuilder sb = new StringBuilder();
    for (String path : paths) {
      sb.append(buildHTMLTag(pathPrefix + path));
    }

    return sb.toString();
  }

  protected abstract String assetType();

  protected abstract String fileSuffix();

  protected abstract String buildHTMLTag(String path);
}
