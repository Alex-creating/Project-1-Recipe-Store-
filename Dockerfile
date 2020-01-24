From maven:latest
COPY . /build
WORKDIR /build
RUN mvn clean package -DskipTests

FROM java:8
WORKDIR /opt/BeanWithScopes
COPY --from=0 /build/target/BeanWithScopes.jar docker-spring-app.jar

ENTRYPOINT ["/usr/bin/java","-jar","docker-spring-app.jar"]
