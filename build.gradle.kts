import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  java
  id("org.springframework.boot") version "2.1.4.RELEASE"
  id("io.spring.dependency-management") version "1.0.7.RELEASE"
  id("com.github.ben-manes.versions") version "0.21.0"
}

version = "0.1.0-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
  mavenLocal()
  mavenCentral()
}

configurations {
  implementation.get().exclude(mapOf("module" to "spring-boot-starter-logging"))
  implementation.get().exclude(mapOf("module" to "spring-boot-starter-tomcat"))
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-jetty")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-aop")
  implementation("org.springframework.boot:spring-boot-starter-cache")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-freemarker")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.session:spring-session-data-redis")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-log4j2")

  runtimeOnly("org.springframework.boot:spring-boot-devtools")
  testImplementation("org.springframework.boot:spring-boot-starter-test")

  // Automatically generate configuration meta-data file from items annotated with @ConfigurationProperties
  compileOnly("org.springframework.boot:spring-boot-configuration-processor")

  implementation("org.postgresql:postgresql:42.2.5")
  implementation("org.flywaydb:flyway-core:5.2.4")
  implementation("joda-time:joda-time:2.10.1")
  implementation("org.asciidoctor:asciidoctorj:1.6.2")

  annotationProcessor("org.projectlombok:lombok:1.18.6")
  compileOnly("org.projectlombok:lombok:1.18.6")
}


tasks.register<Delete>("assetClean") {
  group = "assets"
  description = "Clean built assets"
  delete("$buildDir/assets")
}

tasks.register<Exec>("assetCompile") {
  group = "assets"
  description = "Compile assets"
  dependsOn(":assetClean")
  mkdir(buildDir)
  commandLine = listOf("broccoli", "build", "$buildDir/assets")
}

// Without this directive any additional-spring-configuration-metadata.json files will not be processed
tasks.compileJava {
  dependsOn(tasks.processResources)
}

tasks.bootJar {
  dependsOn(":assetCompile")
  from("$buildDir/assets") {
    into("assets")
  }
  requiresUnpack("**/jruby-complete*.jar", "**/asciidoctorj-*.jar")
}


tasks.named<DependencyUpdatesTask>("dependencyUpdates") {
  resolutionStrategy {
    componentSelection {
      all {
        val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea")
          .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-+]*") }
          .any { it.matches(candidate.version) }
        if (rejected) {
          reject("Release candidate")
        }
      }
    }
  }
}
