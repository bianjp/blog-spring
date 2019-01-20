# Blog

A blog site written in Spring Boot. Used by [https://bianjp.com](https://bianjp.com/).

## Requirement

* Java 8
* PostgreSQL >= 9.0
* Redis

Extra development/building requirement:

* Node.js >= 6
* [Yarn](https://yarnpkg.com/en/)
* [broccoli-cli](https://github.com/broccolijs/broccoli-cli)

## Configuration

### Profiles

[Spring Profiles](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-profiles) are used to separate configurations for different environments.

This project has two profiles:

* development: Used for development environment
* production: Used for production environment

Only save common and non-sensitive configurations in `src/main/resource/application-{profile}.yml` files.

Local specific or sensitive configuration (eg: database password) can be passed in the following ways:

* `src/main/resource/config/application-{profile}.yml`: Git ignored, will be included in packaged jar
* `application-{profile}.yml` or `config/application-{profile}.yml` in working directory (it's project root in development environment): Git ignored, will not be included in packaged jar
* os environment variable
* Java system properties specified in command line
* command line arguments

For example, to specify redis host and datasource url by command line arguments:

```bash
java -jar build/libs/blog-spring-0.1.0-SNAPSHOT.jar --spring.profiles.active=production --spring.redis.host=127.0.0.1 --spring.datasource.url=jdbc:postgresql://localhost/blog
```

See [Externalized Configuration](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/reference/htmlsingle/#boot-features-external-config) for more details about configuration methods and precedence.

### Minimal configuration

You should at least config datasource and redis.

Create an empty database, and [Flyway](https://flywaydb.org/) will take care of the schema migrations.

## Running

### Development

Install npm dependencies if you haven't:

```bash
yarn install
```

Then just start Spring Boot in your IDE or using gradle (`./gradlew bootRun`).

By default `BroccoliServer` will start a `broccoli serve` process listening on localhost:4200. Change `assets.*` properties as needed.

### Production

```bash
./gradlew build -x test
java -jar build/libs/blog-0.1.0-SNAPSHOT.jar --spring.profiles.active=production
```

#### Systemd

If you just need a single instance, using systemd is a good idea.

Create `/etc/systemd/system/blog.service`:

```
[Unit]
Description=Blog site
After=syslog.target

[Service]
User=YOUR_NORMAL_USER
WorkingDirectory=/path/to/working-directory
ExecStart=/usr/bin/java -jar /path/to/blog-0.1.0-SNAPSHOT.jar --spring.profiles.active=production --server.port=8080
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```

```bash
sudo systemctl start blog
sudo systemctl enable blog
```

## License

This project is licensed under the terms of the MIT license.
