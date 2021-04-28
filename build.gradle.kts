import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  java
  id("org.springframework.boot") version "2.4.5"
  id("io.spring.dependency-management") version "1.0.11.RELEASE"
  id("com.github.ben-manes.versions") version "0.38.0"
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
  // https://youtrack.jetbrains.com/issue/IDEA-187868
  // https://github.com/spring-io/start.spring.io/issues/4
  compileOnly {
    extendsFrom(configurations.annotationProcessor.get())
  }
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

  implementation("org.postgresql:postgresql")
  implementation("org.flywaydb:flyway-core")
  implementation("joda-time:joda-time:2.10.10")
  implementation("org.asciidoctor:asciidoctorj:2.5.0")

  // Developer tools
  runtimeOnly("org.springframework.boot:spring-boot-devtools")
  // Automatically generate configuration meta-data file from items annotated with @ConfigurationProperties
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  annotationProcessor("org.projectlombok:lombok")

  // Test
  testImplementation("org.springframework.boot:spring-boot-starter-test")
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

tasks.withType<Test> {
  useJUnitPlatform()
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
