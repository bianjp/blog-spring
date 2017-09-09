package com.bianjp.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@ConfigurationProperties(prefix = "uploads")
public class UploadProperties {
  private String storageDir;

  public String getStorageDir() {
    return storageDir;
  }

  public String getStorageDirUri() {
    return new File(storageDir).toURI().toString();
  }

  public void setStorageDir(String storageDir) throws IOException {
    File file = new File(storageDir);
    if (!file.exists()) {
      if (!file.mkdir()) {
        throw new RuntimeException("Failed to create storage directory: " + storageDir);
      }
    } else if (!file.isDirectory() && !file.getCanonicalFile().isDirectory()) {
      throw new RuntimeException(storageDir + " is not a directory");
    }
    this.storageDir = file.getAbsolutePath();
  }
}
