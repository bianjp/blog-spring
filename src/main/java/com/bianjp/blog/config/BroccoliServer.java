package com.bianjp.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;

@Configuration
@EnableScheduling
@ConditionalOnProperty("assets.broccoli.enabled")
public class BroccoliServer {
  private final AssetConfig.Broccoli broccoliConfig;

  private Process process = null;
  private File brocfile = new File("Brocfile.js");
  private long lastModified = brocfile.lastModified();

  @Autowired
  public BroccoliServer(AssetConfig assetConfig) {
    this.broccoliConfig = assetConfig.getBroccoli();
  }

  private void start() {
    ProcessBuilder processBuilder =
        new ProcessBuilder(
            "broccoli",
            "serve",
            "--host",
            broccoliConfig.getHost(),
            "--port",
            Integer.toString(broccoliConfig.getPort()));
    processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
    processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
    try {
      process = processBuilder.start();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  private void stop() {
    if (process != null && process.isAlive()) {
      process.destroy();
    }
  }

  private void restart() {
    stop();
    start();
  }

  @Bean
  public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener() {
    return event -> start();
  }

  @Bean
  public ApplicationListener<ContextClosedEvent> contextClosedEventApplicationListener() {
    return event -> stop();
  }

  // If Brocfile.js changed, restart broccoli to take effect
  // Use lastModified to detect file change. WatchService can only watch directory
  @Scheduled(fixedDelay = 10000)
  public void watchBrocfile() {
    if (brocfile.lastModified() > lastModified) {
      restart();
    }
  }
}
