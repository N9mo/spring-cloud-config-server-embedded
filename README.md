# spring-cloud-config-server-embedded
Demo Spring Boot application with embedded Spring Cloud Config Server and Spring Boot Actuator for updating properties 
at run time from remote configuration storage. 

**Spring Cloud Config:**
Spring Cloud configuration provides support for externalized configuration in a distributed system. 
With the Config Server, you have a central place to manage external properties for applications across all environments. 
The concept identically to the Spring Environment and PropertySource abstractions,so they fit very well with 
Spring applications but can be used with any application running in any language. 
As an application moves through the deployment pipeline from dev to test and into production, you can manage the 
configuration between those environments and be certain that applications have everything they need to run when they migrate. 
The default implementation of the server storage backend uses git, so it easily supports labelled versions
of configuration environments as well as being accessible to a wide range of tooling for managing the content.

**_Embedding_ the Spring Cloud Config Server:**
The Config Server runs best as a standalone application. However, if need be, you can embed it in another application. 
An optional property named spring.cloud.config.server.bootstrap can be useful in this case. It is a flag to indicate 
whether the server should configure itself from its own remote repository. By default, the flag is off, because it can 
delay startup. However, when embedded in another application, it makes sense to initialize the same way as any other application:
***bootstrap.yml***
```
spring:
  cloud:
    config:
      server:
        bootstrap: true
```
**Configuration repository:**
Spring Cloud Config Server pulls configuration from various sources (e.g. JDBC compatible database, local filesystems etc.). 
Current implementation uses a GitHub public repository, which is very convenient for managing upgrades and physical 
environments and for auditing changes:
***bootstrap.yml***
```
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/N9mo/spring-cloud-configuration.git
```
**Resources:**
- Spring Cloud configuration server [cloud.spring.io](https://cloud.spring.io/spring-cloud-config/multi/multi__spring_cloud_config_server.html)
- Embedding the Config Server [cloud.spring.io](https://cloud.spring.io/spring-cloud-config/multi/multi__embedding_the_config_server.html)

**Spring Boot Actuator:**
Spring Actuator provides different endpoints for health, metrics, and configs, but nothing for refreshing beans. 
Thus, we need Spring Cloud to add a /refresh endpoint to it. This endpoint reloads all property sources of Environment 
and then publishes an EnvironmentChangeEvent:
```
POST: http://host:port/actuator/refresh
```
Spring Cloud also has introduced _@RefreshScope_, and we can use it for configuration classes or beans. 
As a result, the default scope will be refresh instead of singleton.
Using refresh scope, Spring will clear its internal cache of these components on an EnvironmentChangeEvent. 
Then, on the next access to the bean, a new instance is created.

**Resources:**
- Spring Boot Actuator Web API Documentation [docs.spring.io](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/actuator-api/html/#metrics-retrieving-metric)


