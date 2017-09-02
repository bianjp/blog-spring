package com.bianjp.blog.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.servlet.resource.ResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.List;

// Resolve asset request to upstream server
public class AssetUpstreamResolver implements ResourceResolver {
  private AssetConfig assetConfig;

  public AssetUpstreamResolver(AssetConfig assetConfig) {
    this.assetConfig = assetConfig;
  }

  @Override
  public Resource resolveResource(
      HttpServletRequest request,
      String requestPath,
      List<? extends Resource> locations,
      ResourceResolverChain chain) {
    try {
      return new UrlResource(assetConfig.getUpstream() + requestPath);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String resolveUrlPath(
      String resourcePath, List<? extends Resource> locations, ResourceResolverChain chain) {
    return null;
  }
}
