# Blog

A blog site written in Spring Boot. Used by [https://bianjp.com](https://bianjp.com/).

## Requirement

* Java 8
* PostgreSQL >= 9.0
* Redis

Extra development/building requirement:

* Linux
* Node.js >= 6
* [Yarn](https://yarnpkg.com/en/)
* [broccoli-cli](https://github.com/broccolijs/broccoli-cli)
* [inotify-tools](https://github.com/rvoicilas/inotify-tools)

## Configuration

### Profiles

[Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-profiles) are used to separate configurations for different environments.

This project has 3 profiles:

* development: Used for development environment
* production: Used for production environment
* local: Git-ignored. Used to override development/production configuration, or to save sensitive information (eg. database password) 

If you are running a pre-packaged jar, you may have to use environment variable or command line arguments to override configurations. For example:

```bash
java -jar build/libs/blog-spring-0.1.0-SNAPSHOT.jar --spring.profiles.active=production,local --spring.redis.host=127.0.0.1
```

__Don't forget to add "local" to active profiles if you depend on it.__

### Minimal configuration

You should at least config datasource and redis.

Create an empty database, and [Flyway](https://flywaydb.org/) will take care of the schema migrations.

## Running

### Development

Run the following command in the project root directory to serve assets:

```bash
./assets.sh
```

And then start Spring Boot in your IDE or using gradle (`./gradlew bootRun`).

### Production

```bash
./gradlew build
java -jar build/libs/blog-spring-0.1.0-SNAPSHOT.jar --spring.profiles.active=production,local
```

## License

This project is licensed under the terms of the MIT license.
