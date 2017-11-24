package com.bianjp.blog.config.freemarker;

import com.bianjp.blog.config.AssetConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class AssetPathHelper implements TemplateMethodModelEx {
  static class Manifest {
    // Must be public to allow ObjectMapper to access without getter/setter
    public Map<String, String> assets;
    public String prepend;
  }

  private final AssetConfig assetConfig;

  @Autowired
  public AssetPathHelper(AssetConfig assetConfig) {
    this.assetConfig = assetConfig;
  }

  @Value("classpath:assets/manifest.json")
  private Resource manifestResource;

  private Manifest manifest;

  @PostConstruct
  public void parseManifestFile() throws Exception {
    if (assetConfig.isDevelopment()) {
      return;
    }
    if (manifestResource == null || !manifestResource.exists()) {
      throw new RuntimeException("Missing assets manifest file!");
    }
    ObjectMapper mapper = new ObjectMapper();
    try (InputStream inputStream = manifestResource.getInputStream()) {
      manifest = mapper.readValue(inputStream, Manifest.class);
    }
  }

  @Override
  public Object exec(List list) throws TemplateModelException {
    if (!(list.get(0) instanceof SimpleScalar)) {
      throw new TemplateModelException("Wrong parameter for assetPath: Only string permitted!");
    }

    String originalPath = ((SimpleScalar) list.get(0)).getAsString();
    if (assetConfig.isDevelopment()) {
      return "/assets/" + originalPath;
    }

    String digestedPath = manifest.assets.get(originalPath);
    if (digestedPath == null || digestedPath.isEmpty()) {
      throw new TemplateModelException("Asset " + originalPath + " not found!");
    } else {
      return "/assets/" + digestedPath;
    }
  }
}
